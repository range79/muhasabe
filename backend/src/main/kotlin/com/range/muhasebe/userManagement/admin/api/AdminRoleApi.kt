package com.range.muhasebe.userManagement.admin.api
import com.range.muhasebe.userManagement.user.dto.UserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("\${api.prefix}/admin/group")
interface AdminRoleApi {
    @PatchMapping("/make-moderator/{userId}")
    fun makeModerator(@PathVariable userId: Long)
    @PatchMapping("/remove-moderator/{userId}")
    fun removeModerator(@PathVariable userId: Long)

}