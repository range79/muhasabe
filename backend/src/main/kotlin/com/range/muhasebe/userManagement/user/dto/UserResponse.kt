package com.range.muhasebe.userManagement.user.dto

import com.range.muhasebe.userManagement.user.domain.model.Role
import java.util.UUID


data class UserResponse (
    val id : UUID? = null,
    val username : String,
    var role : Role,
)