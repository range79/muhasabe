package com.range.muhasebe.usermanagement.user.domain.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(name = "users")
@SQLRestriction("deleted = false")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    @Column(unique = true, nullable = false)
    val username : String,
    @Column(unique = true, nullable = false)
    @Email
    val email : String,
    var password : String,
    @Enumerated(EnumType.STRING)
    var role : Role,
    var deleted: Boolean = false,
)
