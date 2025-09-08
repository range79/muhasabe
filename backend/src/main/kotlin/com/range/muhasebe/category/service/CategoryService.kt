package com.range.muhasebe.category.service

import com.range.muhasebe.category.domain.model.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface CategoryService {
    fun addCategory(name: String)
    fun removeCategory(id: Long)
    fun findMyOwnCategories(pageable: Pageable): Page<Category>

}