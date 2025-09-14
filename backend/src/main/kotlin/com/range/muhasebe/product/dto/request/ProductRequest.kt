package com.range.muhasebe.product.dto.request

import com.range.muhasebe.product.dto.request.ProductVariantRequest
import java.util.UUID

data class ProductRequest(
    val name: String,
    val description: String,
    val categoryId: UUID,
    val variants: MutableList<ProductVariantRequest> = mutableListOf()
)