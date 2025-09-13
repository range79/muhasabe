package com.range.muhasebe.userManagement.user.domain.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "users")
@SQLRestriction("deleted = false")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id : UUID? = null,
    @Column(unique = true, nullable = false)
    val username : String,
    var firstName : String,
    var lastName : String,
    @Column(unique = true, nullable = false)
    @Email
    val email : String,
    var password : String,
    @Enumerated(EnumType.STRING)
    var role : Role,
    var deleted: Boolean = false,
    var workerPermissions: WorkerPermissions?,
    var phoneNUmber : String? = null,
    val startDate : LocalDateTime?,
)
{
    fun delete() {
        deleted = true
    }
    fun restore(){
        deleted = false
    }
}