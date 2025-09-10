package com.eloboostum.usermanagement.admin.service

interface AdminRoleService {
    fun makeOwner(userId: Long)
    fun makerOwner(userId: Long)
}