package com.range.muhasebe.product.domain.model

import com.github.f4b6a3.uuid.UuidCreator
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*


@Entity
@Table(name = "product_variant")
data class ProductVariant(
    @Id
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    val product: Product,
    @Column(nullable = false)
    var name: String, // örn: "512GB"
    var price: BigDecimal,
    var quantity: Int,
    var barcode: String? = null

){
    @PrePersist
    fun prePersist() {
        if (id == null) {
            id = UuidCreator.getTimeOrdered()
        }
    }
}