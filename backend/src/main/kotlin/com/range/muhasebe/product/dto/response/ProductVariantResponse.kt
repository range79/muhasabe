package com.range.muhasebe.product.dto.response

import java.util.UUID

data class ProductVariantResponse(
    val id: UUID,
    val name: String,
    val price: Int,
    val quantity: Int
)