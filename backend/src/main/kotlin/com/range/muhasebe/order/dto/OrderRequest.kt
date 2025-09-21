package com.range.muhasebe.order.dto

import java.util.UUID

data class OrderRequest (
     val customerId: UUID,
     val productVariantId: UUID,
    val quantity: Int,
)