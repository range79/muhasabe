package com.range.muhasebe.product.dto.request

import com.range.muhasebe.product.domain.model.Product
import com.range.muhasebe.product.domain.model.ProductVariant
import java.util.UUID

data class ProductVariantRequest(
    val name: String,
    val price: Int,
    val quantity: Int,
    val product: Product
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