package com.range.muhasebe.product.service

import com.range.muhasebe.product.domain.model.Product
import com.range.muhasebe.product.dto.request.ProductRequest
import com.range.muhasebe.product.dto.response.ProductResponse

interface ProductVariantService {
    fun createProduct(request: ProductRequest): ProductResponse



}