package com.range.muhasebe.product.dto.response


data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val categoryId: Long,
    val variants: List<ProductVariantResponse> = listOf()
)