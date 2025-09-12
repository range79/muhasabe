package com.range.muhasebe.product.dto.response

data class ProductVariantResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val quantity: Int
)