package com.range.muhasebe.order.controller

import com.range.muhasebe.order.api.OrderApi
import com.range.muhasebe.order.domain.model.Order
import com.range.muhasebe.order.dto.OrderRequest
import com.range.muhasebe.order.service.OrderService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID
@RestController
class OrderController(
    private val orderService: OrderService
):OrderApi {
    override fun createOrder(orderRequest: OrderRequest) {
        return orderService.createOrder(orderRequest)
    }

    override fun getOrders(
        userId: UUID,
        pageable: Pageable
    ): Page<Order> {
        return orderService.getOrders(userId, pageable)
    }

    override fun getAllOrders(pageable: Pageable): Page<Order> {
        return orderService.getAllOrders(pageable)
    }

    override fun cancelOrder(orderId: UUID) {
        return orderService.cancelOrder(orderId)
    }

    override fun acceptOrder(orderId: UUID) {
        return orderService.acceptOrder(orderId)
    }

    override fun getOrderById(orderId: UUID): Order {
        return orderService.getOrderById(orderId)
    }

    override fun getUserAllDebt(userId: UUID): BigDecimal {
        return orderService.getUserAllDebt(userId)
    }

    override fun getTotalDebt(): BigDecimal {
        return orderService.getTotalDebt()
    }
}