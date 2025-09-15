package com.range.muhasebe.customers.domain.repository

import com.range.muhasebe.customers.domain.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CustomerRepository: JpaRepository<Customer, UUID> {
}