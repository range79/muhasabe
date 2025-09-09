package com.range.muhasebe.category.controller

import com.range.muhasebe.category.api.CategoryApi
import com.range.muhasebe.category.domain.model.Category
import com.range.muhasebe.category.service.CategoryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController(
    private val categoryService: CategoryService
): CategoryApi {
    override fun addCategory(name: String) {
        return categoryService.addCategory(name)
    }

    override fun removeCategory(id: Long) {
        return categoryService.removeCategory(id)
    }

    override fun findMyOwnCategories(pageable: Pageable): Page<Category> {
        return categoryService.findMyOwnCategories(pageable)
    }
}