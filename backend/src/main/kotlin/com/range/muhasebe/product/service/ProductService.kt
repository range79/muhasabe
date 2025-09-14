package com.range.muhasebe.product.service

import com.range.muhasebe.product.dto.request.ProductRequest
import com.range.muhasebe.product.dto.response.ProductResponse
import java.util.UUID

interface ProductService {

    fun createProduct(request: ProductRequest): ProductResponse

    fun updateProduct(id: UUID, request: ProductRequest): ProductResponse

    fun deleteProduct(id: UUID)

    fun getProductById(id: UUID): ProductResponse

    fun getAllProducts(): List<ProductResponse>

    fun getProductsByCategory(categoryId: Long): List<ProductResponse>
}
