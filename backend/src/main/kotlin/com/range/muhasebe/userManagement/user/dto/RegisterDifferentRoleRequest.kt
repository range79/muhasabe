package com.range.muhasebe.userManagement.user.dto

import com.range.muhasebe.userManagement.user.domain.model.Role
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty


data class RegisterDifferentRoleRequest (
    @field:NotEmpty
    val firstname: String,
    @field:NotEmpty
    val lastname: String,
    @field:NotEmpty
    val username: String,
    @field:NotEmpty
    val password: String,
    @field:NotEmpty
    @field:Email
    val email: String,
    val role: Role

)
