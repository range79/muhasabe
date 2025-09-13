package com.range.muhasebe.product.dto.response

import java.util.UUID


data class ProductResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val categoryId: Long,
    val variants: List<ProductVariantResponse> = listOf()
)