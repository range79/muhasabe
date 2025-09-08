package com.range.muhasebe.userManagement.owner.dto

import java.time.LocalDateTime

data class WorkerResponse (
    var username: String,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String?,
    var email: String,
    var startDate: LocalDateTime?
)