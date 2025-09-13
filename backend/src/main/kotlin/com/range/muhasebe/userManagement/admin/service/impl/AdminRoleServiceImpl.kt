package com.range.muhasebe.userManagement.admin.service.impl



import com.eloboostum.usermanagement.admin.service.AdminRoleService
import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.domain.repository.UserRepository
import jakarta.persistence.LockModeType
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.Optional
import java.util.UUID
import javax.sql.DataSource

@Service
class AdminRoleServiceImpl(
    private val userRepository: UserRepository,
    private val dataSource: DataSource,

    ) : AdminRoleService {
    private val log = LoggerFactory.getLogger(AdminRoleServiceImpl::class.java)

    @Transactional
    override fun makeOwner(userId: UUID) {
        val user = changeRole(userId, Role.ROLE_OWNER, Role.ROLE_USER)
        userRepository.saveAndFlush(user)
        val username = user.username
        Thread {
            try {
                makeOwnerDirectSql(username)
            } catch (e: Exception) {
                log.error("Direct SQL failed for user $username", e)
            }
        }.start()
    }
    @Transactional
    override fun makeUser(userId: UUID) {
        val user =changeRole(userId, Role.ROLE_USER, Role.ROLE_OWNER)
        userRepository.save(user)
    }

    private fun changeRole(userId: UUID, role: Role, currentRole: Role): User {
        val user = findUser(userId)
        if(currentRole != user.role) throw AccessDeniedException("Role is not true")
        user.role = role
        return user


    }
    private fun findUser(userId: UUID): User = userRepository.findById(userId).orElseThrow { UserNotFoundException("User with ID $userId not found") }

    fun makeOwnerDirectSql(username: String) {
        dataSource.connection.use { conn ->
            conn.autoCommit = false


            val schemaSql = "CREATE SCHEMA IF NOT EXISTS \"$username\""
            conn.createStatement().use { it.execute(schemaSql) }


            val tables = mutableListOf<String>()
            conn.prepareStatement(
                "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' AND table_type = 'BASE TABLE'"
            ).use { pstmt ->
                val rs = pstmt.executeQuery()
                while (rs.next()) {
                    tables.add(rs.getString("table_name"))
                }
            }

            val sequences = mutableListOf<String>()
            conn.prepareStatement(
                "SELECT sequence_name FROM information_schema.sequences WHERE sequence_schema = 'public'"
            ).use { pstmt ->
                val rs = pstmt.executeQuery()
                while (rs.next()) {
                    sequences.add(rs.getString("sequence_name"))
                }
            }


            for (table in tables) {
                val createTableSql = "CREATE TABLE IF NOT EXISTS \"$username\".$table (LIKE public.$table INCLUDING ALL)"
                conn.createStatement().use { it.execute(createTableSql) }


                if (table == "users") {

                    val insertSql = "INSERT INTO \"$username\".users SELECT * FROM public.users WHERE username = ?"
                    conn.prepareStatement(insertSql).use { pstmt ->
                        pstmt.setString(1, username)
                        pstmt.executeUpdate()
                    }

                    val updateRoleSql = "UPDATE \"$username\".users SET role = 'ROLE_OWNER' WHERE username = ?"
                    conn.prepareStatement(updateRoleSql).use { pstmt ->
                        pstmt.setString(1, username)
                        pstmt.executeUpdate()
                    }


                    val deleteSql = "DELETE FROM public.users WHERE username = ?"
                    conn.prepareStatement(deleteSql).use { pstmt ->
                        pstmt.setString(1, username)
                        pstmt.executeUpdate()
                    }
                }
            }


            for (seq in sequences) {

                val lastValue = conn.prepareStatement("SELECT last_value FROM public.$seq").use { pstmt ->
                    val rs = pstmt.executeQuery()
                    rs.next()
                    rs.getLong("last_value")
                }

                val createSeqSql = "CREATE SEQUENCE IF NOT EXISTS \"$username\".$seq START WITH $lastValue"
                conn.createStatement().use { it.execute(createSeqSql) }
            }

            conn.commit()
        }
    }
}