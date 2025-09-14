package com.range.muhasebe.product.service.impl

import com.range.muhasebe.product.domain.model.Product
import com.range.muhasebe.product.domain.repository.ProductRepository
import com.range.muhasebe.product.dto.request.ProductRequest
import com.range.muhasebe.product.dto.response.ProductResponse
import com.range.muhasebe.product.service.CategoryService
import com.range.muhasebe.product.service.ProductService
import org.springframework.stereotype.Service
import java.util.UUID
@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val categoryService: CategoryService
): ProductService {
    override fun createProduct(request: ProductRequest): ProductResponse {
        var request+ productRepository.save(request.toProduct)
    }

    override fun updateProduct(
        id: UUID,
        request: ProductRequest
    ): ProductResponse {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(id: UUID) {
        TODO("Not yet implemented")
    }

    override fun getProductById(id: UUID): ProductResponse {
        TODO("Not yet implemented")
    }

    override fun getAllProducts(): List<ProductResponse> {
        TODO("Not yet implemented")
    }

    override fun getProductsByCategory(categoryId: Long): List<ProductResponse> {
        TODO("Not yet implemented")
    }
    fun ProductRequest.toProduct(): Product{
      val category=  categoryService.findCategory(this.categoryId)
        return Product(
            id = null,
            name =this.name,
            description =this.description,
            category = category,
            variants = this.variants
        )
    }
}