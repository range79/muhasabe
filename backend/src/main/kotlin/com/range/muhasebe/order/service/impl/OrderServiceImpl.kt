package com.range.muhasebe.order.service.impl


import com.range.muhasebe.order.domain.model.Order
import com.range.muhasebe.order.domain.model.OrderStatus
import com.range.muhasebe.order.domain.repository.OrderRepository
import com.range.muhasebe.order.dto.OrderRequest
import com.range.muhasebe.order.exception.OrderNotFoundException
import com.range.muhasebe.order.service.OrderService
import com.range.muhasebe.product.domain.repository.ProductVariantRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val productVariantRepository: ProductVariantRepository
): OrderService {
    override fun createOrder(orderRequest: OrderRequest) {
        val variant = productVariantRepository.findById(orderRequest.productVariantId)
            .orElseThrow { IllegalArgumentException("Product variant not found") }

        val priceAtPurchase = variant.price
        val total = priceAtPurchase.multiply(orderRequest.quantity.toBigDecimal())

        val order = Order(
            customerId = orderRequest.customerId,
            productVariantId = variant.id!!,
            priceAtPurchase = priceAtPurchase,
            quantity = orderRequest.quantity,

            totalPrice = total,
            status = OrderStatus.UNPAID
        )

        orderRepository.save(order)
    }

    override fun getOrders(
        userId: UUID,
        pageable: Pageable
    ): Page<Order> {
        return orderRepository.findOrdersById(userId,pageable)
    }

    override fun getAllOrders(pageable: Pageable): Page<Order> {
        return orderRepository.findAll(pageable)
    }

    override fun deleteOrder(orderId: UUID) {
        val order =getOrderById(orderId)
        orderRepository.delete(order)
    }

    override fun getOrderById(orderId: UUID): Order {
        return orderRepository.findById(orderId).orElseThrow{OrderNotFoundException("Order Not Found")}
    }
    override fun getUserAllDebt(userId: UUID): BigDecimal {
        val orders = orderRepository.findOrdersByCustomerIdAndStatus(userId, OrderStatus.UNPAID)

        return orders
            .map { it.totalPrice }
            .fold(BigDecimal.ZERO) { acc, price -> acc + price }
    }

    override fun getTotalDebt(): BigDecimal {
        val orders =orderRepository.findOrdersByStatus(OrderStatus.UNPAID)
        return orders
            .map { it.totalPrice }
            .fold(BigDecimal.ZERO) { acc, price -> acc + price }
    }


}





