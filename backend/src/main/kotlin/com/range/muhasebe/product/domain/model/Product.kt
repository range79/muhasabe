package com.range.muhasebe.product.domain.model

import com.github.f4b6a3.uuid.UuidCreator
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "product")
data class Product (
    @Id
    var id: UUID? = null,
    var name: String,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    var category: Category,
    var description: String,
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var variants: MutableList<ProductVariant> = mutableListOf()
){
    @PrePersist
    fun prePersist() {
        if (id == null) {
            id = UuidCreator.getTimeOrdered()
        }
    }
}