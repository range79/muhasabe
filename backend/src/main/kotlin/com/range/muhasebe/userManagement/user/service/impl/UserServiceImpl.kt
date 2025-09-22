package com.range.muhasebe.userManagement.user.service.impl


import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.common.config.tenant.TenantContext
import com.range.muhasebe.common.util.SecurityContextUtil
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.domain.repository.UserRepository
import com.range.muhasebe.userManagement.user.dto.MyInfo
import com.range.muhasebe.userManagement.user.dto.RegisterDifferentRoleRequest
import com.range.muhasebe.userManagement.user.dto.UserResponse
import com.range.muhasebe.userManagement.user.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val securityContextUtil: SecurityContextUtil,
): UserService {
    override fun getUserById(userId: UUID): User {
        val user = userRepository.findById(userId).orElseThrow{
            UserNotFoundException("User not found with id: $userId")
        }
        return user
    }


    @Transactional
    override fun registerDifferentRole(registerDifferentRoleRequest: RegisterDifferentRoleRequest) : User{
        return userRepository.save(
            differentRoleRegister(registerDifferentRoleRequest)
        )
        
    }

    override fun getUserByRole(pageable: Pageable,role: Role): Page<User>  {
        return userRepository.findAllByRole(pageable=pageable,role =role)
    }
    @Transactional
    override fun updateUser(user: User): User {
        return userRepository.save(user)
    }
    @Transactional(readOnly = true)
    override fun getDeletedUsersByRole(pageable: Pageable,role: Role): Page<User> {
        val tenant =TenantContext.getTenant()
        return userRepository.findDeletedUsersByRole(pageable= pageable,role=role,tenant=tenant)
    }
    @Transactional(readOnly = true)
    override fun getDeletedUserById(userId: UUID): User {
        return userRepository.findDeletedUserById(userId).orElseThrow {
            UserNotFoundException("User Not Found")
        }
    }
    @Transactional(readOnly = true)
    override fun getUsersByRole(pageable: Pageable,role: Role): Page<User> {
        return userRepository.findAllByRole(pageable=pageable,role=role)
    }

    override fun me(): MyInfo {
    val user =    userRepository.findById(securityContextUtil.getCurrentUserId()).orElseThrow{
        UserNotFoundException("User not found")
    }
        return MyInfo(
            username = user.username,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            role = user.role,
            workerPermissions = user.workerPermissions,
            phoneNUmber = user.phoneNUmber,
            startDate = user.startDate,
            twoFactorenabled = user.twoFactorenabled
        )
    }

    @Transactional(readOnly = true)
    override fun getAllUsers(pageable: Pageable): Page<UserResponse> {
        return userRepository.findAll(pageable)
            .map {user-> userToUserResponse(user)}
    }

    fun userToUserResponse(user: User): UserResponse{
        return UserResponse(
            user.id,
            user.username,
            user.role
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
            phoneNUmber = null,
            startDate = LocalDateTime.now(),
        )
    }
}
