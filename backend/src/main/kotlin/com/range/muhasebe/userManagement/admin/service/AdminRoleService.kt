package com.eloboostum.usermanagement.admin.service

import com.range.muhasebe.userManagement.user.domain.model.WorkerPermissions
import java.util.UUID

interface AdminRoleService {
    fun makeOwner(userId: UUID)
    fun makeUser(userId: UUID)

}