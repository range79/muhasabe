package com.range.muhasebe.common.config.tenant

import org.hibernate.context.spi.CurrentTenantIdentifierResolver


object TenantContext {
    private val currentTenant = ThreadLocal<String>()

    fun setTenant(tenantId: String?) {
        currentTenant.set(tenantId)
    }

    fun getTenant(): String = currentTenant.get() ?: "public"

    fun clear() {
        currentTenant.remove()
    }
}
