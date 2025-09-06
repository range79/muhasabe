package com.range.muhasebe.usermanagement.admin.api


import com.range.muhasebe.usermanagement.user.dto.UserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("\${api.prefix}/admin/user")
interface AdminUserApi {
    @DeleteMapping("/delete/{userID}")
    fun deleteUser(@PathVariable userID: Long)
    @DeleteMapping("/{userId}/permanent")
    fun deleteUserPermanently(userId: Long)
    @GetMapping("/deleted/all")
    fun getDeletedUsers(pageable: Pageable): Page<UserResponse>
    @GetMapping("/restore/{userId}")
    fun restoreUser( @PathVariable userId: Long)

}