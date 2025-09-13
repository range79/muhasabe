package com.range.muhasebe.product.domain.model

import com.range.muhasebe.product.dto.response.ProductVariantResponse
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table


@Entity
@Table(name = "product_variant")
data class ProductVariant(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_variant_seq")
    @SequenceGenerator(name = "product_variant_seq", sequenceName = "product_variant_seq", allocationSize = 1)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    val product: Product,

    var name: String, // örn: "512GB"
    var price: Int,
    var quantity: Int

)