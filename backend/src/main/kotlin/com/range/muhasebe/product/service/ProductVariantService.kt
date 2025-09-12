package com.range.muhasebe.product.service

import com.range.muhasebe.product.domain.model.Product

interface ProductVariantService {
    fun addVariant(id: Long, product): Product



}