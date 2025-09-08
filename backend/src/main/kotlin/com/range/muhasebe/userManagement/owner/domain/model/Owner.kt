package com.range.muhasebe.userManagement.owner.domain.model

import com.range.muhasebe.userManagement.user.domain.model.User
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "owner_profile")
data class Owner (
    @Id
    @MapsId("userId")
    var id: Long,
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    var user: User,
    var phoneNumber: String? = null,
)