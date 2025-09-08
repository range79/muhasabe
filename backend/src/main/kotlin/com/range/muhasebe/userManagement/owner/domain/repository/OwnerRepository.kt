package com.range.muhasebe.userManagement.owner.domain.repository

import com.range.muhasebe.userManagement.owner.domain.model.Owner
import org.springframework.data.jpa.repository.JpaRepository

interface OwnerRepository: JpaRepository<Owner, Long> {
}