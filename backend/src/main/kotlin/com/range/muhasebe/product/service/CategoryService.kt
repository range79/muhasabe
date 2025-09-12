package com.range.muhasebe.product.service

import com.range.muhasebe.product.domain.model.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CategoryService {
    fun addCategory(name: String)
    fun removeCategory(id: Long)
    fun findCategories(pageable: Pageable): Page<Category>

}