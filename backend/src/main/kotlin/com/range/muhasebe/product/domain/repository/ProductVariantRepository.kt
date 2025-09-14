package com.range.muhasebe.product.domain.repository

import com.range.muhasebe.product.domain.model.ProductVariant
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductVariantRepository: JpaRepository<ProductVariant, UUID> {

}