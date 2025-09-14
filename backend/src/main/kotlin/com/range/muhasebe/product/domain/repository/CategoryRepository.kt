package com.range.muhasebe.product.domain.repository

import com.range.muhasebe.product.domain.model.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CategoryRepository: JpaRepository<Category, UUID> {

}