package com.eloboostum.usermanagement.admin.service

import java.util.UUID

interface AdminRoleService {
    fun makeOwner(userId: UUID)
    fun makeUser(userId: UUID)
}