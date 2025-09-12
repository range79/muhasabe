package com.range.muhasebe.product.api

import com.range.muhasebe.product.domain.model.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.*

@RequestMapping("\${api.prefix}/categories")
interface CategoryApi {

    @PostMapping
    fun addCategory(@RequestParam name: String)

    @DeleteMapping("/{id}")
    fun removeCategory(@PathVariable id: Long)

    @GetMapping
    fun findMyOwnCategories(pageable: Pageable): Page<Category>
}
