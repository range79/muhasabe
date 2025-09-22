package com.range.muhasebe.userManagement.admin.api
import com.range.muhasebe.userManagement.user.domain.model.WorkerPermissions
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*
import kotlin.enums.EnumEntries

@RequestMapping("\${api.prefix}/admin/role")
interface AdminRoleApi {
    @PatchMapping("/make-owner/{userId}")
    fun makeOwner(@PathVariable userId: UUID)
    @PatchMapping("/remove-owner/{userId}")
    fun removeOwner(@PathVariable userId: UUID)

}