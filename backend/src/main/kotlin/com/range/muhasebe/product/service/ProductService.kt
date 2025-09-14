package com.range.muhasebe.product.service

import com.range.muhasebe.product.domain.model.Product
import com.range.muhasebe.product.dto.request.ProductRequest
import com.range.muhasebe.product.dto.response.ProductResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface ProductService {

    fun createProduct(request: ProductRequest)

    fun updateProduct(id: UUID, request: ProductRequest): ProductResponse

    fun deleteProduct(id: UUID)

    fun getProductById(id: UUID): Product

    fun getAllProducts(): Page<ProductResponse>

    fun getProductsByCategory(categoryId: UUID,pageable: Pageable): Page<ProductResponse>
}
