package com.range.muhasebe.category.service.impl

import com.range.muhasebe.category.domain.model.Category
import com.range.muhasebe.category.domain.repository.CategoryRepository
import com.range.muhasebe.category.exception.CategoryNotFoundException
import com.range.muhasebe.category.service.CategoryService
import com.range.muhasebe.common.exception.RoleMismatchException
import com.range.muhasebe.common.util.SecurityContextUtil
import com.range.muhasebe.userManagement.user.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryServiceImpl
    (
    private val categoryRepository: CategoryRepository,

    private val securityContextUtil: SecurityContextUtil

): CategoryService {
    @Transactional
    override fun addCategory(name: String) {
        val userid =securityContextUtil.getCurrentUserId()



        val category = Category(
            id = null,
            name = name,
            ownerID = userid,
        )
        categoryRepository.save(category)
    }
    @Transactional
    override fun removeCategory(id: Long) {
        val userId = securityContextUtil.getCurrentUserId()
        val category = findCategory(id)

        if (category.ownerID != userId) {
            throw RoleMismatchException("You are not allowed to remove this category")
        }
        categoryRepository.delete(category)
    }
    @Transactional(readOnly = true)
    override fun findMyOwnCategories(pageable: Pageable): Page<Category> {
        val userId = securityContextUtil.getCurrentUserId()

        return categoryRepository.findCategoryByOwnerID(userId, pageable)
    }


    private fun findCategory(id: Long): Category {
        return categoryRepository.findById(id).orElseThrow{CategoryNotFoundException()}
    }

}

