package com.range.muhasebe.product.service.impl

import com.range.muhasebe.product.domain.model.Category
import com.range.muhasebe.product.domain.repository.CategoryRepository
import com.range.muhasebe.product.exception.CategoryNotFoundException
import com.range.muhasebe.product.service.CategoryService
import com.range.muhasebe.common.exception.RoleMismatchException
import com.range.muhasebe.common.util.SecurityContextUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CategoryServiceImpl
    (
    private val categoryRepository: CategoryRepository,

    ): CategoryService {
    @Transactional
    override fun addCategory(name: String) {
        val category = Category(
            id = null,
            name = name,
        )
        categoryRepository.save(category)
    }
    @Transactional
    override fun removeCategory(id: UUID) {
        val category = findCategory(id)
        categoryRepository.delete(category)
    }
    @Transactional(readOnly = true)
    override fun findCategories(pageable: Pageable): Page<Category> {
        return categoryRepository.findAll(pageable)
    }
    @Transactional
    override fun updateCategory(id: UUID, name: String) {
        val category = findCategory(id)
        category.name = name
        categoryRepository.save(category)
    }


    override fun findCategory(id: UUID): Category {
        return categoryRepository.findById(id).orElseThrow{CategoryNotFoundException()}
    }

}

