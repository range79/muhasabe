package com.range.muhasebe.usermanagement.admin.service.impl



import com.eloboostum.usermanagement.admin.service.AdminRoleService
import com.range.muhasebe.usermanagement.user.domain.model.Role
import com.range.muhasebe.usermanagement.user.domain.repository.UserRepository
import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.usermanagement.common.exception.RoleMismatchException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminRoleServiceImpl(
    private val userRepository: UserRepository
) : AdminRoleService {
    @Transactional
    override fun makeModerator(userId: Long) {
        changeRole(userId, Role.ROLE_MODERATOR, Role.ROLE_USER)
    }
    @Transactional
    override fun removeModerator(userId: Long) {
        changeRole(userId, Role.ROLE_USER, Role.ROLE_MODERATOR)
    }

    private fun changeRole(userId: Long, role: Role, currentRole: Role) {
        if(currentRole == role) throw RoleMismatchException("Role is not true")
        val user = findUser(userId)
        user.role = role
        userRepository.save(user)
    }
    private fun findUser(userId: Long) = userRepository.findById(userId).orElseThrow { UserNotFoundException("User with ID $userId not found") }
}