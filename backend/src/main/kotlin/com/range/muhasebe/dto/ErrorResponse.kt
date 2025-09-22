package com.range.muhasebe.dto

import java.time.LocalDateTime

data class ErrorResponse(
    var status: String,
    var message: String,
    var timestamp: LocalDateTime
)