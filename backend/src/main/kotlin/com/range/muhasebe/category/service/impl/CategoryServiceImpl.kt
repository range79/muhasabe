package com.range.muhasebe.category.service.impl

import com.range.muhasebe.category.domain.model.Category
import com.range.muhasebe.category.domain.repository.CategoryRepository
import com.range.muhasebe.category.service.CategoryService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl
    (
    private val categoryRepository: CategoryRepository,
            ): CategoryService {
    override fun addCategory(category: String) {
        val category =

    }

    override fun removeCategory(id: Long) {
        TODO("Not yet implemented")
    }

    override fun findAll(): Page<Category> {
        TODO("Not yet implemented")
    }


}

