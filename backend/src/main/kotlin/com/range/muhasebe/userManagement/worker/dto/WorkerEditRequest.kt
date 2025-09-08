package com.range.muhasebe.userManagement.worker.dto

data class WorkerEditRequest (
    var firstName : String,
    var lastName : String,
    val username : String,
    val email : String,
    var password : String,
    var phoneNumber: String,
)