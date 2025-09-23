package com.range.muhasebe.order.service.impl


import com.range.muhasebe.order.domain.model.Order
import com.range.muhasebe.order.domain.model.OrderStatus
import com.range.muhasebe.order.domain.repository.OrderRepository
import com.range.muhasebe.order.dto.OrderRequest
import com.range.muhasebe.order.exception.OrderNotFoundException
import com.range.muhasebe.order.service.OrderService
import com.range.muhasebe.product.domain.model.ProductVariant
import com.range.muhasebe.product.domain.repository.ProductVariantRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val productVariantRepository: ProductVariantRepository
): OrderService {
    @Transactional(readOnly = true)
    override fun createOrder(orderRequest: OrderRequest) {
        val variant = productVariantRepository.findById(orderRequest.productVariantId)
            .orElseThrow { IllegalArgumentException("Product variant not found") }
        if (variant.quantity < orderRequest.quantity) {
            throw IllegalStateException("Not enough stock for product variant")
        }
        variant.quantity -= orderRequest.quantity


        productVariantRepository.save(variant)
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
    @Transactional(readOnly = true)
    override fun getOrders(
        userId: UUID,
        pageable: Pageable
    ): Page<Order> {
        return orderRepository.findOrdersById(userId,pageable)
    }
    @Transactional(readOnly = true)
    override fun getAllOrders(pageable: Pageable): Page<Order> {
        return orderRepository.findAll(pageable)
    }
    @Transactional
    override fun cancelOrder(orderId: UUID) {
        val order = getOrderById(orderId)


        if (order.status == OrderStatus.PAID) {
            throw IllegalStateException("Paid orders cannot be cancelled")
        }

        order.status = OrderStatus.CANCEL


        val variant = findProductVariant(order.productVariantId)
        variant.quantity += order.quantity
        productVariantRepository.save(variant)

        orderRepository.save(order)
    }
    @Transactional
    override fun acceptOrder(orderId: UUID) {
        val order = getOrderById(orderId)
        if (order.status != OrderStatus.UNPAID) {
            throw IllegalStateException("Only unpaid orders can be accepted")
        }
        order.status = OrderStatus.PAID
        orderRepository.save(order)
    }

    @Transactional
    override fun updateOrder(orderId: UUID, orderRequest: OrderRequest) {
        val order = getOrderById(orderId)
        val oldVariant = findProductVariant(order.productVariantId)


        if (order.productVariantId != orderRequest.productVariantId) {

            oldVariant.quantity += order.quantity
            productVariantRepository.save(oldVariant)


            val newVariant = productVariantRepository.findById(orderRequest.productVariantId)
                .orElseThrow { IllegalArgumentException("Product variant not found") }

            if (newVariant.quantity < orderRequest.quantity) {
                throw IllegalStateException("Not enough stock for the selected product variant")
            }


            newVariant.quantity -= orderRequest.quantity
            productVariantRepository.save(newVariant)

            order.productVariantId = newVariant.id!!
            order.priceAtPurchase = newVariant.price
        } else if (order.quantity != orderRequest.quantity) {

            val diff = orderRequest.quantity - order.quantity
            if (oldVariant.quantity < diff) {
                throw IllegalStateException("Not enough stock to increase quantity")
            }
            oldVariant.quantity -= diff
            productVariantRepository.save(oldVariant)
            order.quantity = orderRequest.quantity
            order.totalPrice = order.priceAtPurchase.multiply(order.quantity.toBigDecimal())
        }


        order.customerId = orderRequest.customerId

        orderRepository.save(order)
    }



    @Transactional(readOnly = true)
    override fun getOrderById(orderId: UUID): Order {
        return orderRepository.findById(orderId).orElseThrow{OrderNotFoundException("Order Not Found")}
    }
    override fun getUserAllDebt(userId: UUID): BigDecimal {
        val orders = orderRepository.findOrdersByCustomerIdAndStatus(userId, OrderStatus.UNPAID)

        return orders
            .map { it.totalPrice }
            .fold(BigDecimal.ZERO) { acc, price -> acc + price }
    }
    @Transactional(readOnly = true)
    override fun getTotalDebt(): BigDecimal {
        val orders =orderRepository.findOrdersByStatus(OrderStatus.UNPAID)
        return orders
            .map { it.totalPrice }
            .fold(BigDecimal.ZERO) { acc, price -> acc + price }
    }
    private fun findProductVariant(productVariantId: UUID): ProductVariant {
        return productVariantRepository.findById(productVariantId)
            .orElseThrow { IllegalArgumentException("Product variant not found") }
    }

}





