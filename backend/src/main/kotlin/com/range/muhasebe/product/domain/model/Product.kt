package com.range.muhasebe.product.domain.model

import com.range.muhasebe.product.dto.request.ProductVariantRequest
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "product")
data class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    val id: UUID? = null,
    var name: String,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    var category: Category,
    var description: String,
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var variants: List<ProductVariantRequest> = mutableListOf()
)