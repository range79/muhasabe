package com.range.muhasebe.product.dto.response

import com.range.muhasebe.product.domain.model.Category
import com.range.muhasebe.product.domain.model.ProductVariant
import java.util.*

data class ProductDetailedResponse(
    val id: UUID? = null,
    var name: String,
    var category: Category,
    var description: String,
    var variants: MutableList<ProductVariant> = mutableListOf()
)