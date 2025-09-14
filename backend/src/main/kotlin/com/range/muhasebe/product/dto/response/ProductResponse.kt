package com.range.muhasebe.product.dto.response

import com.range.muhasebe.product.domain.model.Category
import java.util.UUID


data class ProductResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val category: Category,
    val variants: List<ProductVariantResponse> = listOf()
)