package com.range.muhasebe.userManagement.user.service.impl

import com.range.muhasebe.common.util.SecurityContextUtil
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.domain.repository.UserRepository
import com.range.muhasebe.userManagement.user.dto.MyInfo
import com.range.muhasebe.userManagement.user.dto.RegisterDifferentRoleRequest
import com.range.muhasebe.userManagement.user.dto.UserResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import java.util.*

class UserServiceImplTest {

    private lateinit var userRepository: UserRepository
    private lateinit var passwordEncoder: PasswordEncoder
    private lateinit var securityContextUtil: SecurityContextUtil
    private lateinit var userService: UserServiceImpl
    private lateinit var user: User

    @BeforeEach
    fun setUp() {
        userRepository = mock(UserRepository::class.java)
        passwordEncoder = mock(PasswordEncoder::class.java)
        securityContextUtil = mock(SecurityContextUtil::class.java)
        userService = UserServiceImpl(userRepository, passwordEncoder, securityContextUtil)


        user = User(
            id = UUID.randomUUID(),
            username = "testuser",
            password = "encodedPass",
            email = "test@test.com",
            role = Role.ROLE_USER,
            deleted = false,
            firstName = "Test",
            lastName = "User",
            startDate = LocalDateTime.now(),
            phoneNUmber = "123456789",
            twoFactorenabled = true
        )
    }

    @Test
    fun `getUserById returns user when found`() {
        `when`(userRepository.findById(user.id!!)).thenReturn(Optional.of(user))
        val result = userService.getUserById(user.id!!)
        assertEquals(user, result)
    }

    @Test
    fun `getUserById throws UserNotFoundException when not found`() {
        `when`(userRepository.findById(user.id!!)).thenReturn(Optional.empty())
        assertThrows(Exception::class.java) { userService.getUserById(user.id!!) }
    }

    @Test
    fun `registerDifferentRole saves and returns user`() {
        val request = RegisterDifferentRoleRequest(
            email = "new@test.com",
            password = "pass",
            username = "newuser",
            role = Role.ROLE_ADMIN,
            firstname = "New",
            lastname = "User"
        )
        val savedUser = User(
            id = UUID.randomUUID(),
            email = request.email,
            password = "encodedPass",
            username = request.username,
            role = request.role,
            deleted = false,
            firstName = request.firstname,
            lastName = request.lastname,
            phoneNUmber = null,
            startDate = LocalDateTime.now()
        )

        `when`(passwordEncoder.encode(request.password)).thenReturn("encodedPass")
        `when`(userRepository.save(any(User::class.java))).thenReturn(savedUser)

        val result = userService.registerDifferentRole(request)
        assertEquals(savedUser, result)
        verify(userRepository).save(any(User::class.java))
    }

    @Test
    fun `updateUser calls save and returns updated user`() {
        `when`(userRepository.save(user)).thenReturn(user)
        val result = userService.updateUser(user)
        assertEquals(user, result)
        verify(userRepository).save(user)
    }

    @Test
    fun `me returns current user info`() {
        `when`(securityContextUtil.getCurrentUserId()).thenReturn(user.id)
        `when`(userRepository.findById(user.id!!)).thenReturn(Optional.of(user))

        val myInfo: MyInfo = userService.me()
        assertEquals(user.username, myInfo.username)
        assertEquals(user.email, myInfo.email)
        assertEquals(user.role, myInfo.role)
        assertEquals(user.twoFactorenabled, myInfo.twoFactorenabled)
    }

    @Test
    fun `getUsersByRole returns page of users`() {
        val role = Role.ROLE_USER
        val pageable = PageRequest.of(0, 10)
        val page: Page<User> = PageImpl(listOf(user), pageable, 1)

        `when`(userRepository.findAllByRole(pageable, role)).thenReturn(page)
        val result = userService.getUsersByRole(pageable, role)
        assertEquals(1, result.totalElements)
        assertEquals(user, result.content[0])
    }

    @Test
    fun `getAllUsers returns page of UserResponse`() {
        val pageable = PageRequest.of(0, 10)
        val page: Page<User> = PageImpl(listOf(user), pageable, 1)

        `when`(userRepository.findAll(pageable)).thenReturn(page)
        val result: Page<UserResponse> = userService.getAllUsers(pageable)
        assertEquals(1, result.totalElements)
        assertEquals(user.id, result.content[0].id)
        assertEquals(user.username, result.content[0].username)
        assertEquals(user.role, result.content[0].role)
    }
}
