package com.range.muhasebe.userManagement.user.service

import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.dto.LoginRequest
import com.range.muhasebe.userManagement.user.dto.RegisterDifferentRoleRequest
import com.range.muhasebe.userManagement.user.dto.RegisterRequest


interface AuthService
{
    fun login(loginRequest: LoginRequest): String
    fun register(registerRequest: RegisterRequest): String
    fun forgotPassword(token: String,password: String)
    fun forgotPasswordMailSender(email:String)

}