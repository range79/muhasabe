package com.range.muhasebe.order.domain.repository

import com.range.muhasebe.order.domain.model.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderRepository : JpaRepository<Order, UUID>{
    fun findByUserId(userId: UUID,pageable: Pageable): Page<Order>
}