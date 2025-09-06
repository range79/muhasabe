package com.range.muhasebe.usermanagement.user.api

import com.range.muhasebe.usermanagement.user.dto.LoginRequest
import com.range.muhasebe.usermanagement.user.dto.RegisterRequest
import com.range.muhasebe.usermanagement.user.dto.UserResponse
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@RateLimiter(name = "auth")
@RequestMapping("\${api.prefix}/auth")
interface  AuthApi
{
    //    @PostMapping("/login")
//    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Void>
//    @PostMapping("/register")
//    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<Void>
    @RateLimiter(name = "login")
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest):String
    @PostMapping("/register")
    @RateLimiter(name = "register")
    fun register(@RequestBody @Valid registerRequest: RegisterRequest): String
    @RateLimiter(name = "forgotPasswordRequest")
    @PostMapping("/forgot-Password")
    fun forgotPasswordRequest(@Email @RequestBody email: String)
    @RateLimiter(name = "resetPassword")
    @PostMapping("/reset-Password")
    fun resetPassword(@RequestParam(name = "token") token: String, @RequestBody password: String)
  }