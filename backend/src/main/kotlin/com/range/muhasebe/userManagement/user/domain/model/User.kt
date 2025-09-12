package com.range.muhasebe.userManagement.user.domain.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(schema = "public", name = "users")
@SQLRestriction("deleted = false")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    @Column(unique = true, nullable = false)
    val username : String,
    var firstName : String,
    var lastName : String,
    @Column(unique = true, nullable = false)
    @Email
    val email : String,
    val tenantId : String?,
    var password : String,
    @Enumerated(EnumType.STRING)
    var role : Role,
    var deleted: Boolean = false,
)
