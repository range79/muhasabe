package com.range.muhasebe.userManagement.admin.api
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@RequestMapping("\${api.prefix}/admin/group")
interface AdminRoleApi {
    @PatchMapping("/make-owner/{userId}")
    fun makeOwner(@PathVariable userId: UUID)
    @PatchMapping("/remove-owner/{userId}")
    fun removeOwner(@PathVariable userId: UUID)

}