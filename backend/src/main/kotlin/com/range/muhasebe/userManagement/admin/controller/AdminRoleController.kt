package com.range.muhasebe.userManagement.admin.controller
import com.eloboostum.usermanagement.admin.service.AdminRoleService
import com.range.muhasebe.userManagement.admin.api.AdminRoleApi

import org.springframework.web.bind.annotation.RestController

@RestController
class AdminRoleController(
    private  val service: AdminRoleService
): AdminRoleApi {
    override fun makeOwner(userId: Long) {
       return  service.makeOwner(userId)
    }

    override fun removeOwner(userId: Long) {
       return  service.makeUser(userId)
    }


}