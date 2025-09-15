package com.range.muhasebe.order.dto

import java.util.UUID

data class OrdersResponse (
    val id: UUID? = null,
    val productid: UUID? = null,
    val user: UUID?
)