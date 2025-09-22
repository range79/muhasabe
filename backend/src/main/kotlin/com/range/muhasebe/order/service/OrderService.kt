package com.range.muhasebe.order.service

import com.range.muhasebe.order.domain.model.Order
import com.range.muhasebe.order.dto.OrderRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.math.BigDecimal
import java.util.UUID

interface  OrderService {
    fun createOrder(orderRequest: OrderRequest)
    fun getOrders(userId: UUID,pageable: Pageable): Page<Order>
    fun getAllOrders(pageable: Pageable): Page<Order>
    fun cancelOrder(orderId: UUID)
    fun acceptOrder(orderId: UUID)
    fun getOrderById(orderId: UUID): Order
    fun getUserAllDebt(userId: UUID): BigDecimal
    fun getTotalDebt(): BigDecimal

}