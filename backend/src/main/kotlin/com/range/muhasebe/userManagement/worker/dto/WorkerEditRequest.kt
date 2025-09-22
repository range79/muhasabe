package com.range.muhasebe.userManagement.worker.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class WorkerEditRequest (
    @field:NotBlank
    var firstName : String,
    @field:NotBlank
    var lastName : String,
    @field:NotBlank
    val username : String,
    @field:NotBlank
    @field:Email
    val email : String,
    @field:NotBlank
    @field:Min(8,)
    var password : String,
    @field:NotBlank
    var phoneNumber: String,
)