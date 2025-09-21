package com.range.muhasebe.customers.service

import com.range.muhasebe.customers.domain.model.Customer
import com.range.muhasebe.customers.dto.CustomerAddRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface CustomerService {

    fun createCustomer(customer: CustomerAddRequest)
    fun getCustomer(id: UUID): Customer
    fun getCustomers(pageable: Pageable): Page<Customer>
    fun deleteCustomer(id: UUID)

}