package com.range.muhasebe.category.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Category (
    @Id
    var id: Long? = null,
    var name: String,
    var ownerID: Long,
)