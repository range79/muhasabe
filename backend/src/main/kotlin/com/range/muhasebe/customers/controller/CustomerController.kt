package com.range.muhasebe.customers.controller

import com.range.muhasebe.customers.api.CustomerApi
import com.range.muhasebe.customers.domain.model.Customer
import com.range.muhasebe.customers.dto.CustomerAddRequest
import com.range.muhasebe.customers.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
@RestController
class CustomerController(
    private val customerService: CustomerService

): CustomerApi {
    override fun createCustomer(customer: CustomerAddRequest) {
return customerService.createCustomer(customer)
    }

    override fun getCustomer(customerId: UUID): Customer {
        return customerService.getCustomer(customerId)
    }

    override fun getCustomers(pageable: Pageable): Page<Customer> {
        return customerService.getCustomers(pageable)
    }

    override fun deleteCustomer(customerId: UUID) {
        return customerService.deleteCustomer(customerId)
    }
}