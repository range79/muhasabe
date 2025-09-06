package com.range.muhasebe.usermanagement.admin.controller
import com.eloboostum.usermanagement.admin.service.AdminRoleService
import com.range.muhasebe.usermanagement.admin.api.AdminRoleApi

import org.springframework.web.bind.annotation.RestController

@RestController
class AdminRoleController(
    private  val service: AdminRoleService
): AdminRoleApi {
    override fun makeModerator(userId: Long) {
       return  service.makeModerator(userId)
    }

    override fun removeModerator(userId: Long) {
       return  service.removeModerator(userId)
    }


}