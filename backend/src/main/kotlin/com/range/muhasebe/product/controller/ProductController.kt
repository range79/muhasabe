package com.range.muhasebe.product.controller

import com.range.muhasebe.product.api.ProductApi
import com.range.muhasebe.product.domain.model.Product
import com.range.muhasebe.product.dto.request.ProductRequest
import com.range.muhasebe.product.dto.response.ProductResponse
import com.range.muhasebe.product.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
@RestController
class ProductController(
    private val productService: ProductService
): ProductApi {
    override fun createProduct(request: ProductRequest) {
        return productService.createProduct(request)
    }

    override fun updateProduct(
        id: UUID,
        request: ProductRequest
    ): ProductResponse {
        return productService.updateProduct(id, request)
    }

    override fun deleteProduct(id: UUID) {
        return productService.deleteProduct(id)
    }

    override fun getProductById(id: UUID): Product {
        return productService.getProductById(id)
    }

    override fun getAllProducts(): Page<ProductResponse> {
        return productService.getAllProducts()
    }

    override fun getProductsByCategory(
        categoryId: UUID,
        pageable: Pageable
    ): Page<ProductResponse> {
        return productService.getProductsByCategory(categoryId,pageable)
    }
}