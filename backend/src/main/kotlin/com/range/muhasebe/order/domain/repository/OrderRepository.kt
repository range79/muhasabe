package com.range.muhasebe.order.domain.repository

import com.range.muhasebe.order.domain.model.Order
import com.range.muhasebe.order.domain.model.OrderStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderRepository : JpaRepository<Order, UUID>{
    fun findOrdersById(
        id: UUID,
        pageable: Pageable
    ): Page<Order>

    fun findOrdersByCustomerIdAndStatus(customerId: UUID, status: OrderStatus): MutableList<Order>
    fun findOrdersByStatus(status: OrderStatus): MutableList<Order>
}