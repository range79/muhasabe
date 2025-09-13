package com.range.muhasebe.product.controller

import com.range.muhasebe.product.api.CategoryApi
import com.range.muhasebe.product.domain.model.Category
import com.range.muhasebe.product.service.CategoryService
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
        return categoryService.findCategories(pageable)
    }
}