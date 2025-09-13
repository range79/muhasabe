package com.range.muhasebe.common.config.tenant

import org.hibernate.context.spi.CurrentTenantIdentifierResolver

class CurrentTenantIdentifierResolverImpl : CurrentTenantIdentifierResolver<String> {
    override fun resolveCurrentTenantIdentifier(): String {
        println(TenantContext.getTenant())
    return    TenantContext.getTenant()
    }

    override fun validateExistingCurrentSessions() = true
}
