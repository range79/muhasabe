package com.range.muhasebe.order.service.impl

import com.range.muhasebe.order.domain.repository.OrderRepository
import com.range.muhasebe.order.dto.OrdersResponse
import com.range.muhasebe.order.service.OrderService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository
): OrderService {
    override fun createOrder(userId: UUID, productId: UUID) {
        TODO("Not yet implemented")
    }

    override fun getOrders(userId: UUID): Page<OrdersResponse> {
        TODO("Not yet implemented")
    }

    override fun getAllOrders(): Page<OrdersResponse> {
        TODO("Not yet implemented")
    }

    override fun deleteOrder(orderId: UUID) {
        TODO("Not yet implemented")
    }

    override fun deleteOrders() {
        TODO("Not yet implemented")
    }


}





