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
    val userId: UUID,
    val productId: UUID,
    val price: BigDecimal,
    val quantity: Int,
    val totalPrice: Int,
    val isPaid: Boolean,
    val isCompleted: Boolean,
    val isCancelled: Boolean,
    val createdAt: LocalDateTime=LocalDateTime.now(),
    val updatedAt: LocalDateTime=LocalDateTime.now()
){

}