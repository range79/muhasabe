package com.range.muhasebe.product.domain.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*


@Entity
@Table(name = "product_variant")
data class ProductVariant(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    val product: Product,

    var name: String, // örn: "512GB"
    var price: BigDecimal,
    var quantity: Int

)