package com.range.muhasebe.usermanagement.user.dto

import com.range.muhasebe.usermanagement.user.domain.model.Role

data class UserResponse (
    val id : Long? = null,
    val username : String,
    var role : Role,
)