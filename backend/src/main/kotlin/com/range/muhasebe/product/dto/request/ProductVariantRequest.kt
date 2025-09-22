package com.range.muhasebe.product.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class ProductVariantRequest(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val price: BigDecimal,
    @field:NotNull
    var quantity: Int,
    @field:NotNull
    var barcode: String? = null
)