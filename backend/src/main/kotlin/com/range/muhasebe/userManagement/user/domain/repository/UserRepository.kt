package com.range.muhasebe.userManagement.user.domain.repository

import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import jakarta.persistence.LockModeType

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.util.*

interface  UserRepository : JpaRepository<User, UUID>{

    fun findByUsername(username: String): User

    @Query(
        value = "SELECT * FROM users WHERE deleted = true ORDER BY id DESC",
        countQuery = "SELECT COUNT(*) FROM users WHERE deleted = true",
        nativeQuery = true
    )
    fun getDeletedUsers(pageable: Pageable): Page<User>

    @Query(
        value = "SELECT * FROM users WHERE userId = :userId AND deleted = true",
        nativeQuery = true
    )
    fun findDeletedUserById(userId: UUID): Optional<User>




    @Query(
        value = "SELECT * FROM users u WHERE u.deleted = true AND u.role = :role AND u.tenant_id = :tenant",
        countQuery = "SELECT count(*) FROM users u WHERE u.deleted = true AND u.role = :role AND u.tenant_id = :tenant",
        nativeQuery = true
    )
    fun findDeletedUsersByRole(pageable: Pageable, role: Role, tenant: String): Page<User>

    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<User>
    fun findAllByRole(pageable: Pageable,role: Role): Page<User>
}