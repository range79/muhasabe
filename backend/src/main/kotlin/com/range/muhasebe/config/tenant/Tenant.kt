package com.range.muhasebe.config.tenant

object Tenant {
    private val CURRENT_TENANT = ThreadLocal<String?>()

    fun get(): String? {
        return CURRENT_TENANT.get()
    }

    fun set(tenantId: String?) {
        CURRENT_TENANT.set(tenantId)
    }

    fun unset() {
        CURRENT_TENANT.remove()
    }
}