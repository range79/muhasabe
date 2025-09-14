package com.range.muhasebe.product.dto.response

import com.range.muhasebe.product.domain.model.Category
import java.util.UUID


data class ProductResponse(
    val id: UUID?,
    val name: String,
)