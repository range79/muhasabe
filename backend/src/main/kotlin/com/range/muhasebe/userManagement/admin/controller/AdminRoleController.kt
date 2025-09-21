package com.range.muhasebe.userManagement.admin.controller
import com.eloboostum.usermanagement.admin.service.AdminRoleService
import com.range.muhasebe.userManagement.admin.api.AdminRoleApi
import com.range.muhasebe.userManagement.user.domain.model.WorkerPermissions

import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import kotlin.enums.EnumEntries

@RestController
class AdminRoleController(
    private  val service: AdminRoleService
): AdminRoleApi {
    override fun makeOwner(userId: UUID) {
       return  service.makeOwner(userId)
    }

    override fun removeOwner(userId: UUID) {
       return  service.makeUser(userId)
    }





}