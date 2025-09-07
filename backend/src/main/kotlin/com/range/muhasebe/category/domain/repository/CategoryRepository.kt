package com.range.muhasebe.category.domain.repository

import com.range.muhasebe.category.domain.model.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
    fun findCategoryByOwnerID(ownerID: Long,pageable: Pageable): Page<Category>

}