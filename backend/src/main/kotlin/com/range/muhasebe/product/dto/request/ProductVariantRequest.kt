package com.range.muhasebe.product.dto.request

import com.range.muhasebe.product.domain.model.Product
import com.range.muhasebe.product.domain.model.ProductVariant

data class ProductVariantRequest(
    val name: String,
    val price: Int,
    val quantity: Int,
    val product: Long
){
    fun ProductVariantRequest.toProductVariant(product: Product): ProductVariant {

        return ProductVariant(
            id = null,
            name = name,
            price = price,
            quantity = quantity,
            product = product
        )
    }
}