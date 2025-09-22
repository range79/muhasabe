package com.range.muhasebe.product.api

import com.range.muhasebe.product.domain.model.Product
import com.range.muhasebe.product.dto.request.ProductRequest
import com.range.muhasebe.product.dto.response.ProductResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID
@RequestMapping("\${api.prefix}/products")
interface ProductApi {
    @PostMapping
    fun createProduct(@Validated request: ProductRequest)
    @PatchMapping("/{id}")
    fun updateProduct(@PathVariable id: UUID, @RequestBody @Validated request: ProductRequest): ProductResponse
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: UUID)
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: UUID): Product
    @GetMapping("/all")
    fun getAllProducts(): Page<ProductResponse>
    @GetMapping("/category/{categoryId}")
    fun getProductsByCategory(@PathVariable categoryId: UUID, pageable: Pageable): Page<ProductResponse>
}