package com.range.muhasebe.product.domain

import com.range.muhasebe.category.domain.model.Category
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table

@Entity
@Table(name = "product")
data class Product (
    @Id
    val id:Long? = null,
    var name: String,
    @JoinColumn(name = "category_id")


    var description: String,
    var price: Int,
    var quantity: Int,
)