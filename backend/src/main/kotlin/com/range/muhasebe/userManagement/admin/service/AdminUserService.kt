package com.eloboostum.usermanagement.admin.service


import com.range.muhasebe.userManagement.user.dto.UserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface AdminUserService {
    fun deleteUser(userID: UUID)
    fun deleteUserPermanently(userId: UUID)
    fun getDeletedUsers(pageable: Pageable): Page<UserResponse>
    fun restoreUser(userID: UUID)

}