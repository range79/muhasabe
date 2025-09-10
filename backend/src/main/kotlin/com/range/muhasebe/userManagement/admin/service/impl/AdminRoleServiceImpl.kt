package com.range.muhasebe.userManagement.admin.service.impl



import com.eloboostum.usermanagement.admin.service.AdminRoleService

import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.common.exception.RoleMismatchException
import com.range.muhasebe.config.tenant.DynamicDataSourceProvider
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.domain.repository.UserRepository
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminRoleServiceImpl(
    private val userRepository: UserRepository,
    private val dynamicDataSourceProvider: DynamicDataSourceProvider
) : AdminRoleService {
    @Transactional
    override fun makeOwner(userId: Long) {

        val username= changeRole(userId, Role.ROLE_OWNER, Role.ROLE_USER)

        dynamicDataSourceProvider.getDataSource(username)
    }
    @Transactional
    override fun makerOwner(userId: Long) {
        changeRole(userId, Role.ROLE_USER, Role.ROLE_OWNER)
    }

    private fun changeRole(userId: Long, role: Role, currentRole: Role): String {
        val user = findUser(userId)
        if(currentRole != user.role) throw AccessDeniedException("Role is not true")
        user.role = role
        userRepository.save(user)
        return user.username


    }
    private fun findUser(userId: Long): User = userRepository.findById(userId).orElseThrow { UserNotFoundException("User with ID $userId not found") }
}