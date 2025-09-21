package com.range.muhasebe.customers.api

import com.range.muhasebe.customers.domain.model.Customer
import com.range.muhasebe.customers.dto.CustomerAddRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID
@RequestMapping("\${api.prefix}/customers")
interface CustomerApi {
    @PostMapping
    fun createCustomer(customer: CustomerAddRequest)
    @GetMapping("/{customerId}")
    fun getCustomer(@PathVariable customerId: UUID): Customer
    @GetMapping("/")
    fun getCustomers(pageable: Pageable): Page<Customer>
    @DeleteMapping("/{customerId}")
    fun deleteCustomer(@PathVariable customerId: UUID)
}