package com.range.muhasebe.userManagement.user.service



import com.range.muhasebe.userManagement.user.dto.UserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService {
    fun getUserById(userId: Long): UserResponse
    fun getAllUsers(pageable: Pageable): Page<UserResponse>
}