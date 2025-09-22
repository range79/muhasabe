package com.range.muhasebe.userManagement.user.dto

import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.WorkerPermissions
import java.time.LocalDateTime

data class MyInfo (
    val username : String,
    var firstName : String,
    var lastName : String,
    val email : String,
    var role : Role,
    var workerPermissions: MutableList<WorkerPermissions> = mutableListOf(),
    var phoneNUmber : String? = null,
    val startDate : LocalDateTime?,
    var twoFactorenabled : Boolean = false,
)