package com.range.muhasebe.product.dto.request

import com.range.muhasebe.product.dto.request.ProductVariantRequest
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.UUID

data class ProductRequest(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val description: String,
    @field:NotBlank
    val categoryId: UUID,
    @field:NotNull
    var variants: MutableList<ProductVariantRequest> = mutableListOf()
)