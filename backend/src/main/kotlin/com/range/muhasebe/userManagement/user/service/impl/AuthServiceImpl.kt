package com.range.muhasebe.userManagement.user.service.impl


import com.eloboostum.usermanagement.user.exception.AuthenticationException
import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.common.security.jwt.JWTUtil
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.domain.repository.UserRepository
import com.range.muhasebe.userManagement.user.dto.LoginRequest
import com.range.muhasebe.userManagement.user.dto.RegisterDifferentRoleRequest
import com.range.muhasebe.userManagement.user.dto.RegisterRequest
import com.range.muhasebe.userManagement.user.service.AuthService
import com.range.muhasebe.userManagement.user.service.helper.AuthServiceHelper

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl (
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JWTUtil,
    private val authServiceHelper: AuthServiceHelper
): AuthService {

    @Transactional(readOnly = true)
    override fun login(loginRequest: LoginRequest): String {

        val user =userRepository.findByUsername(loginRequest.username)

        if(!passwordEncoder.matches(loginRequest.password,user.password))
        {
            throw AuthenticationException("UserName or Password Incorrect")
        }

        return jwtUtil.generateToken(user.id,user.role)

    }
    @Transactional
    override fun register(registerRequest: RegisterRequest): String {
        val  user =userRepository.save(registerMapper(registerRequest))
        return jwtUtil.generateToken(user.id,user.role)
    }

    @Transactional
    override fun registerDifferentRole(registerDifferentRoleRequest: RegisterDifferentRoleRequest) {
         userRepository.save(
            differentRoleRegister(registerDifferentRoleRequest)
        )
    }

    override fun forgotPassword(token: String, password: String) {
        val mail = authServiceHelper.getEmailAndConsumeToken(token)
            ?: throw AuthenticationException("Token is Invalid")
        val user =  userRepository.findByEmail(mail).orElseThrow { UserNotFoundException("User not found") }
        user.password=passwordEncoder.encode(password)
        userRepository.save(user)
    }

    override fun forgotPasswordMailSender(email: String) {
        if (!userRepository.existsByEmail(email)) {
            throw UserNotFoundException("User with email $email does not exist")
        }
        return authServiceHelper.sendMail(email)
    }


    fun registerMapper(registerRequest: RegisterRequest): User {
        return User(
            id = null,
            email = registerRequest.email,
            password = passwordEncoder.encode(registerRequest.password),
            username = registerRequest.username,
            role = Role.ROLE_USER,
            deleted = false,
            firstName = registerRequest.firstname,
            lastName = registerRequest.lastname,
        )
    }
    fun differentRoleRegister(r: RegisterDifferentRoleRequest) : User{
        return User(
            id = null,
            email = r.email,
            password = passwordEncoder.encode(r.password),
            username = r.username,
            role = r.role,
            deleted = false,
            firstName = r.firstname,
            lastName = r.lastname,

        )
    }

}