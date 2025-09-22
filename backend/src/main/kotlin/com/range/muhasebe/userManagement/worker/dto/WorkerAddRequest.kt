package com.range.muhasebe.userManagement.worker.dto

import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class WorkerAddRequest (
    @field:NotBlank
    var username: String,
    @field:NotBlank
    var email: String,
    @field:NotBlank
    var password: String,
    @field:NotBlank
    var firstName: String,
    @field:NotBlank
    var lastName: String,
    @field:NotBlank
    var phoneNumber: String,
    @field:NotBlank
    var startDate: LocalDateTime,
)