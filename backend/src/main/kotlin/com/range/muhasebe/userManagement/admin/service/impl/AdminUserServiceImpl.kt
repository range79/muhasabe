package com.range.muhasebe.userManagement.admin.service.impl

import com.eloboostum.usermanagement.admin.service.AdminUserService


import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.userManagement.user.domain.model.User

import com.range.muhasebe.userManagement.user.domain.repository.UserRepository
import com.range.muhasebe.userManagement.user.dto.UserResponse

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserServiceImpl (
    private val userRepository: UserRepository
): AdminUserService {
    @Transactional
    override fun deleteUser(userID: Long) {
        val user = findUser(userID)
        user.deleted=true
        userRepository.save(user)
    }
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    override fun deleteUserPermanently(userId: Long) {
        return userRepository.deleteById(userId)
    }

    override fun getDeletedUsers(pageable: Pageable): Page<UserResponse> {
        return userRepository.getDeletedUsers(pageable)
            .map { user-> UserResponse(user.id,user.username,user.role) }
    }

    override fun restoreUser(userID: Long) {
        val user = userRepository.findDeletedUserById(userID)
            .orElseThrow{UserNotFoundException("Deleted User not Found")}
       user.deleted= false
        userRepository.save(user)
    }
    private fun findUser(userId: Long): User {
        return userRepository.findById(userId).orElseThrow{
            UserNotFoundException("User not Found ")
        }
    }
}