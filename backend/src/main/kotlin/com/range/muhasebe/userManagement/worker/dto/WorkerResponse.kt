package com.range.muhasebe.userManagement.worker.dto

import java.util.UUID

data class WorkerResponse (
    var id: UUID?,
    var username: String,
    var email: String,

    )