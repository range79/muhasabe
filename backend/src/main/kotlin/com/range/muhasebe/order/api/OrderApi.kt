package com.range.muhasebe.order.api

import com.range.muhasebe.order.domain.model.Order
import com.range.muhasebe.order.dto.OrderRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.util.UUID

@RequestMapping("\${api.prefix}/orders")
interface OrderApi {

    @PostMapping
    fun createOrder(@RequestBody orderRequest: OrderRequest)

    @GetMapping("/user/{userId}")
    fun getOrders(@PathVariable userId: UUID, pageable: Pageable): Page<Order>

    @GetMapping
    fun getAllOrders(pageable: Pageable): Page<Order>

    @DeleteMapping("/{orderId}")
    fun deleteOrder(@PathVariable orderId: UUID)

    @GetMapping("/{orderId}")
    fun getOrderById(@PathVariable orderId: UUID): Order

    @GetMapping("/debt/{userId}")
    fun getUserAllDebt(@PathVariable userId: UUID): BigDecimal

    @GetMapping("/debt")
    fun getTotalDebt(): BigDecimal
}
