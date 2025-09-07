package com.range.muhasebe.category.service

import com.range.muhasebe.category.domain.model.Category
import org.springframework.data.domain.Page


interface CategoryService {
    fun addCategory(category: String)
    fun removeCategory(id: Long)
    fun findAll(): Page<Category>

}