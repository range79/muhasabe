package com.range.muhasebe.product.dto.request

data class ProductVariantRequest(
    val name: String,
    val price: Int,
    val quantity: Int
)