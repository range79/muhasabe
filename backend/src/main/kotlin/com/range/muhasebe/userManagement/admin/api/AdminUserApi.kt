package com.range.muhasebe.userManagement.admin.api


import com.range.muhasebe.userManagement.user.dto.UserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@RequestMapping("\${api.prefix}/admin/user")
interface AdminUserApi {
    @DeleteMapping("/delete/{userID}")
    fun deleteUser(@PathVariable userID: UUID)
    @DeleteMapping("/{userId}/permanent")
    fun deleteUserPermanently(userId: UUID)
    @GetMapping("/deleted/all")
    fun getDeletedUsers(pageable: Pageable): Page<UserResponse>
    @GetMapping("/restore/{userId}")
    fun restoreUser( @PathVariable userId: UUID)
    @GetMapping("getusers/all")
    fun getAllUsers(pageable: Pageable): Page<UserResponse>
}