package com.range.muhasebe.customers.domain.model

import com.github.f4b6a3.uuid.UuidCreator
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "customers")
data class Customer (
    @Id
    var id: UUID? = null,
    val username: String,
    val phoneNumber: String,
    val address: String
){
    @PrePersist
    fun prePersist() {
        if (id == null) {
            id = UuidCreator.getTimeOrdered()
        }
    }
}