package com.range.muhasebe.order.domain.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    var customerId: UUID,
    var productVariantId: UUID,
    var quantity: Int,
    var totalPrice: BigDecimal,
    var priceAtPurchase: BigDecimal,
    var status: OrderStatus,
    val createdAt: LocalDateTime=LocalDateTime.now(),
    val updatedAt: LocalDateTime=LocalDateTime.now()
)
