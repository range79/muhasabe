package com.range.muhasebe.customers.service.impl

import com.range.muhasebe.customers.domain.model.Customer
import com.range.muhasebe.customers.domain.repository.CustomerRepository
import com.range.muhasebe.customers.dto.CustomerAddRequest
import com.range.muhasebe.customers.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository
): CustomerService {
    override fun createCustomer(customer: CustomerAddRequest) {
        customerRepository.save(customer.toCustomer())
    }

    override fun getCustomer(id: UUID): Customer {
        return customerRepository.findById(id).orElseThrow()
    }

    override fun getCustomers(pageable: Pageable): Page<Customer> {
        return customerRepository.findAll(pageable)
    }

    override fun deleteCustomer(id: UUID) {
       val customer = getCustomer(id)
        customerRepository.delete(customer)
    }
    private fun CustomerAddRequest.toCustomer(): Customer {
        return Customer(
            username = username,
            phoneNumber = phoneNumber,
            address = address
        )
    }
}