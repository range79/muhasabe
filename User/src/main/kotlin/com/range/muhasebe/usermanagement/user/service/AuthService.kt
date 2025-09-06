package com.range.muhasebe.usermanagement.user.service

import com.range.muhasebe.usermanagement.user.dto.LoginRequest
import com.range.muhasebe.usermanagement.user.dto.RegisterRequest

interface AuthService
{
    fun login(loginRequest: LoginRequest): String
    fun register(registerRequest: RegisterRequest): String
    fun forgotPassword(token: String,password: String)
    fun forgotPasswordMailSender(email:String)
}