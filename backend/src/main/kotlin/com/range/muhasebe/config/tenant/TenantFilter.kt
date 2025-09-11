package com.range.muhasebe.config.tenant

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class TenantFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val host = request.serverName // örn: user1.localhost
            val tenant = host.split(".")[0] // user1
            TenantContext.setTenant(tenant)
            filterChain.doFilter(request, response)
        } finally {
            TenantContext.clear()
        }
    }
}
