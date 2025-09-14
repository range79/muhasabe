package com.range.muhasebe.product.domain.repository

import com.range.muhasebe.product.domain.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository: JpaRepository<Product, UUID> {
    fun findAllByCategoryId(categoryId: UUID,pageable: Pageable): Page<Product>

}