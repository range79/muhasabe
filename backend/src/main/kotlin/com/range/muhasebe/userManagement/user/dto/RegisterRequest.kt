package com.range.muhasebe.userManagement.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

data class RegisterRequest(
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
)