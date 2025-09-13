package com.range.muhasebe.userManagement.worker.dto

import java.time.LocalDateTime
import java.util.UUID

data class WorkerDetailedResponse (
    var id: UUID?,
    var username: String,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String?,
    var email: String,
    var startDate: LocalDateTime?
)