package com.range.muhasebe.usermanagement.admin.service.impl
import com.eloboostum.usermanagement.admin.service.AdminUserService
import com.range.muhasebe.userManagement.admin.api.AdminUserApi
import com.range.muhasebe.userManagement.user.dto.UserResponse

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class AdminUserController(
    private val service: AdminUserService
): AdminUserApi {
    override fun deleteUser(userID: UUID) {
        return service.deleteUser(userID)
    }
    override fun deleteUserPermanently(userId: UUID) {
        return service.deleteUserPermanently(userId)
    }

    override fun getDeletedUsers(pageable: Pageable): Page<UserResponse> {
        return service.getDeletedUsers(pageable)
    }


    override fun restoreUser(userId: UUID) {
        return service.restoreUser(userId)
    }
}