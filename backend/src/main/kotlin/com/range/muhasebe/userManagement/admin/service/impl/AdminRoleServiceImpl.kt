package com.range.muhasebe.userManagement.admin.service.impl



import com.eloboostum.usermanagement.admin.service.AdminRoleService

import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.common.exception.RoleMismatchException
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.domain.repository.UserRepository
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminRoleServiceImpl(
    private val userRepository: UserRepository
) : AdminRoleService {
    @Transactional
    override fun makeModerator(userId: Long) {
        changeRole(userId, Role.ROLE_OWNER, Role.ROLE_USER)
    }
    @Transactional
    override fun removeModerator(userId: Long) {
        changeRole(userId, Role.ROLE_USER, Role.ROLE_OWNER)
    }

    private fun changeRole(userId: Long, role: Role, currentRole: Role) {
        if(currentRole == role) throw AccessDeniedException("Role is not true")
        val user = findUser(userId)
        user.role = role
        userRepository.save(user)
    }
    private fun findUser(userId: Long): User = userRepository.findById(userId).orElseThrow { UserNotFoundException("User with ID $userId not found") }
}