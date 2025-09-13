package com.range.muhasebe.userManagement.user.service



import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.dto.RegisterDifferentRoleRequest
import com.range.muhasebe.userManagement.user.dto.UserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface UserService {
    fun getUserById(userId: UUID): User
    fun getAllUsers(pageable: Pageable): Page<UserResponse>
    fun registerDifferentRole(registerDifferentRoleRequest: RegisterDifferentRoleRequest): User
    fun getUserByRole(pageable: Pageable,role: Role): Page<User>
    fun updateUser(user: User): User
    fun getDeletedUsersByRole(pageable: Pageable,role: Role): Page<User>
    fun getDeletedUserById(userId: UUID): User
}