package com.range.muhasebe.common.config.tenant

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
            val host = request.serverName // example: user1.localhost
            val tenant = if (host.contains(".")) host.split(".")[0] else "public" // user1

            TenantContext.setTenant(tenant)
            filterChain.doFilter(request, response)
        } finally {
            TenantContext.clear()
        }
    }
}
