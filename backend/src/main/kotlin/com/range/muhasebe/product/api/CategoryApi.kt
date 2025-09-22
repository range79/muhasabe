package com.range.muhasebe.product.api

import com.range.muhasebe.product.domain.model.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RequestMapping("\${api.prefix}/categories")
interface CategoryApi {

    @PostMapping
    fun addCategory(@RequestParam @Validated name: String)

    @DeleteMapping("/{id}")
    fun removeCategory(@PathVariable id: UUID)

    @GetMapping
    fun findMyOwnCategories(pageable: Pageable): Page<Category>
    @PatchMapping("/{id}")
    fun updateCategory(@PathVariable id: UUID,@RequestBody @Validated name: String)
}
