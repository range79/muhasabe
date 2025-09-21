package com.range.muhasebe.customers.dto

data class CustomerAddRequest (
    var username: String,
    var phoneNumber: String,
    var address: String
)