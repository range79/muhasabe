package com.range.muhasebe.userManagement.user.dto

import com.range.muhasebe.userManagement.user.domain.model.Role


data class UserResponse (
    val id : Long? = null,
    val username : String,
    var role : Role,
)