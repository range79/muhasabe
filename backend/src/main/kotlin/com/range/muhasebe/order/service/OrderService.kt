package com.range.muhasebe.order.service

import com.range.muhasebe.order.dto.OrdersResponse
import org.springframework.data.domain.Page
import java.util.UUID

interface  OrderService {
    fun createOrder(userId: UUID, productId: UUID)
    fun getOrders(userId: UUID): Page<OrdersResponse>
    fun getAllOrders(): Page<OrdersResponse>
    fun deleteOrder(orderId: UUID)
    fun getOrderById(orderId: UUID): OrdersResponse
}