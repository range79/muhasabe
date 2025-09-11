package com.eloboostum.usermanagement.admin.service

interface AdminRoleService {
    fun makeOwner(userId: Long)
    fun makeUser(userId: Long)
}