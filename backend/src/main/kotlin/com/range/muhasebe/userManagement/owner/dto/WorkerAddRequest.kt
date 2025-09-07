package com.range.muhasebe.userManagement.owner.dto

import java.time.LocalDateTime

data class WorkerAddRequest (
    var username: String,
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String,
    var startDate: LocalDateTime,
)