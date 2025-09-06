package com.range.muhasebe.usermanagement.user.service.impl

import com.range.muhasebe.usermanagement.user.domain.model.User
import com.range.muhasebe.usermanagement.user.domain.repository.UserRepository
import com.range.muhasebe.usermanagement.user.dto.UserResponse
import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.usermanagement.user.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
): UserService {
    override fun getUserById(userId: Long): UserResponse {
        val user = userRepository.findById(userId).orElseThrow{
            UserNotFoundException("User not found with id: $userId")
        }
        return UserResponse(
            id = userId,
            username = user.username,
            role = user.role
        )
    }
    override fun getAllUsers(pageable: Pageable): Page<UserResponse> {
        return userRepository.findAll(pageable)
            .map {user-> userToUserResponse(user)}
    }

    fun userToUserResponse(user: User): UserResponse{
        return UserResponse(
            user.id,
            user.username,
            user.role
        )
    }

}
