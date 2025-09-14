package com.range.muhasebe.product.dto.request

import java.math.BigDecimal

data class ProductVariantRequest(
    val name: String,
    val price: BigDecimal,
    val quantity: Int,
)