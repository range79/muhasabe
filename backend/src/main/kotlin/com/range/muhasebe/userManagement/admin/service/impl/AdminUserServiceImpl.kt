package com.range.muhasebe.userManagement.admin.service.impl


import com.eloboostum.usermanagement.admin.service.AdminUserService
import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.domain.repository.UserRepository
import com.range.muhasebe.userManagement.user.dto.UserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AdminUserServiceImpl (
    private val userRepository: UserRepository
): AdminUserService {
    @Transactional
    override fun deleteUser(userID: UUID) {
        val user = findUser(userID)
        user.deleted=true
        userRepository.save(user)
    }
    @PreAuthorize("hasRole('ADMIN')")
    override fun deleteUserPermanently(userId: UUID) {
        return userRepository.deleteById(userId)
    }

    override fun getDeletedUsers(pageable: Pageable): Page<UserResponse> {
        return userRepository.getDeletedUsers(pageable)
            .map { user-> UserResponse(user.id,user.username,user.role) }
    }

    override fun restoreUser(userID: UUID) {
        val user = userRepository.findDeletedUserById(userID)
            .orElseThrow{UserNotFoundException("Deleted User not Found")}
        user.deleted= false
        userRepository.save(user)
    }

    override fun listAllUsers(pageable: Pageable): Page<UserResponse> {
        return userRepository.findAllByRole(pageable,Role.ROLE_USER)
            .map {UserResponse(it.id,
                it.username,
                it.role)  }
    }

    private fun findUser(userId: UUID): User {
        return userRepository.findById(userId).orElseThrow{
            UserNotFoundException("User not Found ")
        }
    }
}