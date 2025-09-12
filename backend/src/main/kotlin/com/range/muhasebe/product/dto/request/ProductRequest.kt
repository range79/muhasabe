package com.range.muhasebe.product.dto.request

import com.range.muhasebe.product.dto.request.ProductVariantRequest

data class ProductRequest(
    val name: String,
    val description: String,
    val categoryId: Long,
    val variants: List<ProductVariantRequest> = listOf()
)