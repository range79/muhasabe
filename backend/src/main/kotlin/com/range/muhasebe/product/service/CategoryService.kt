package com.range.muhasebe.product.service

import com.range.muhasebe.product.domain.model.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface CategoryService {
    fun addCategory(name: String)
    fun removeCategory(id: UUID)
    fun findCategories(pageable: Pageable): Page<Category>
    fun updateCategory(id: UUID, name: String)
    fun findCategory(id: UUID): Category
}