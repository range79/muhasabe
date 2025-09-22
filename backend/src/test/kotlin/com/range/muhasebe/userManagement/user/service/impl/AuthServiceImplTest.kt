package com.range.muhasebe.userManagement.user.service.impl

import com.eloboostum.usermanagement.user.exception.AuthenticationException
import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.common.security.jwt.JWTUtil
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.domain.repository.UserRepository
import com.range.muhasebe.userManagement.user.dto.LoginRequest
import com.range.muhasebe.userManagement.user.dto.RegisterRequest
import com.range.muhasebe.userManagement.user.service.helper.AuthServiceHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import java.util.*

class AuthServiceImplTest {

    private lateinit var userRepository: UserRepository
    private lateinit var passwordEncoder: PasswordEncoder
    private lateinit var jwtUtil: JWTUtil
    private lateinit var authServiceHelper: AuthServiceHelper
    private lateinit var authServiceImpl: AuthServiceImpl

    @BeforeEach
    fun setUp() {
        userRepository = mock(UserRepository::class.java)
        passwordEncoder = mock(PasswordEncoder::class.java)
        jwtUtil = mock(JWTUtil::class.java)
        authServiceHelper = mock(AuthServiceHelper::class.java)

        authServiceImpl = AuthServiceImpl(userRepository, passwordEncoder, jwtUtil, authServiceHelper)
    }

    @Test
    fun `login with correct credentials and 2FA disabled returns JWT`() {
        val loginRequest = LoginRequest(username = "testuser", password = "pass")
        val user = User(
            id = UUID.randomUUID(),
            username = "testuser",
            password = "encodedpass",
            email = "test@test.com",
            role = Role.ROLE_USER,
            twoFactorenabled = false,
            deleted = false,
            firstName = "Test",
            lastName = "User",
            startDate = LocalDateTime.now(),
            phoneNUmber = null
        )

        `when`(userRepository.findByUsername("testuser")).thenReturn(user)
        `when`(passwordEncoder.matches("pass", "encodedpass")).thenReturn(true)
        `when`(jwtUtil.generateToken(user.id, Role.ROLE_USER)).thenReturn("jwt-token")

        val token = authServiceImpl.login(loginRequest)
        assertEquals("jwt-token", token)
    }

    @Test
    fun `login with 2FA enabled returns null and triggers email`() {
        val loginRequest = LoginRequest(username = "testuser", password = "pass")
        val user = User(
            id = UUID.randomUUID(),
            username = "testuser",
            password = "encodedpass",
            email = "test@test.com",
            role = Role.ROLE_USER,
            twoFactorenabled = true,
            deleted = false,
            firstName = "Test",
            lastName = "User",
            startDate = LocalDateTime.now(),
            phoneNUmber = null
        )

        `when`(userRepository.findByUsername("testuser")).thenReturn(user)
        `when`(passwordEncoder.matches("pass", "encodedpass")).thenReturn(true)

        val token = authServiceImpl.login(loginRequest)
        assertNull(token)
        verify(authServiceHelper).twoFactoryAuth("test@test.com")
    }

    @Test
    fun `login with incorrect password throws AuthenticationException`() {
        val loginRequest = LoginRequest(username = "testuser", password = "wrong")
        val user = User(
            id = UUID.randomUUID(),
            username = "testuser",
            password = "encodedpass",
            email = "test@test.com",
            role = Role.ROLE_USER,
            twoFactorenabled = false,
            deleted = false,
            firstName = "Test",
            lastName = "User",
            startDate = LocalDateTime.now(),
            phoneNUmber = null
        )

        `when`(userRepository.findByUsername("testuser")).thenReturn(user)
        `when`(passwordEncoder.matches("wrong", "encodedpass")).thenReturn(false)

        assertThrows(AuthenticationException::class.java) {
            authServiceImpl.login(loginRequest)
        }
    }

    @Test
    fun `twoFactorAuthentication with valid token returns JWT`() {
        val user = User(
            id = UUID.randomUUID(),
            username = "testuser",
            password = "encodedpass",
            email = "test@test.com",
            role = Role.ROLE_USER,
            twoFactorenabled = true,
            deleted = false,
            firstName = "Test",
            lastName = "User",
            startDate = LocalDateTime.now(),
            phoneNUmber = null
        )

        `when`(authServiceHelper.twoFactoryAuthChecker("valid-token")).thenReturn("test@test.com")
        `when`(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user))
        `when`(jwtUtil.generateToken(user.id, Role.ROLE_USER)).thenReturn("jwt-token")

        val token = authServiceImpl.twoFactorAuthentication("valid-token")
        assertEquals("jwt-token", token)
    }

    @Test
    fun `twoFactorAuthentication with invalid token throws AuthenticationException`() {
        `when`(authServiceHelper.twoFactoryAuthChecker("invalid-token")).thenReturn(null)
        assertThrows(AuthenticationException::class.java) {
            authServiceImpl.twoFactorAuthentication("invalid-token")
        }
    }

    @Test
    fun `register returns JWT`() {
        val registerRequest = RegisterRequest(
            username = "newuser",
            password = "pass",
            email = "new@test.com",
            firstname = "New",
            lastname = "User"
        )

        val savedUser = User(
            id = UUID.randomUUID(),
            username = "newuser",
            password = "encodedpass",
            email = "new@test.com",
            role = Role.ROLE_USER,
            deleted = false,
            firstName = "New",
            lastName = "User",
            startDate = LocalDateTime.now(),
            phoneNUmber = null,
            twoFactorenabled = false
        )

        `when`(passwordEncoder.encode("pass")).thenReturn("encodedpass")
        `when`(userRepository.save(any(User::class.java))).thenReturn(savedUser)
        `when`(jwtUtil.generateToken(savedUser.id, Role.ROLE_USER)).thenReturn("jwt-token")

        val token = authServiceImpl.register(registerRequest)
        assertEquals("jwt-token", token)
    }

    @Test
    fun `forgotPassword updates password`() {
        val user = User(
            id = UUID.randomUUID(),
            username = "testuser",
            password = "oldpass",
            email = "test@test.com",
            role = Role.ROLE_USER,
            twoFactorenabled = false,
            deleted = false,
            firstName = "Test",
            lastName = "User",
            startDate = LocalDateTime.now(),
            phoneNUmber = null
        )

        `when`(authServiceHelper.getEmailAndConsumeToken("token123")).thenReturn("test@test.com")
        `when`(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user))
        `when`(passwordEncoder.encode("newpass")).thenReturn("encodedNewPass")
        `when`(userRepository.save(user)).thenReturn(user)

        authServiceImpl.forgotPassword("token123", "newpass")
        assertEquals("encodedNewPass", user.password)
        verify(userRepository).save(user)
    }

    @Test
    fun `forgotPasswordMailSender throws if email does not exist`() {
        `when`(userRepository.existsByEmail("nonexistent@test.com")).thenReturn(false)
        assertThrows(UserNotFoundException::class.java) {
            authServiceImpl.forgotPasswordMailSender("nonexistent@test.com")
        }
    }

    @Test
    fun `forgotPasswordMailSender calls helper if email exists`() {
        `when`(userRepository.existsByEmail("test@test.com")).thenReturn(true)

        authServiceImpl.forgotPasswordMailSender("test@test.com")
        verify(authServiceHelper).passwordResetRequest("test@test.com")
    }
}
