package com.range.muhasebe.common.security

import com.range.muhasebe.common.config.tenant.TenantFilter
import com.range.muhasebe.common.security.jwt.JWTFilter
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.WorkerPermissions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import reactor.core.scheduler.Scheduler

@Configuration
class SecurityConfig(private val jwtFilter: JWTFilter,
                     private val tenantFilter: TenantFilter) {
    @Value("\${api.prefix}")
    private lateinit var prefix: String



        @Bean
        fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
            http {
                csrf { disable() }
                authorizeHttpRequests {
                    // Auth endpoints
                    authorize("$prefix/auth/**", permitAll)

                    // Swagger
                    authorize("/v3/**", permitAll)
                    authorize("/swagger-ui/**", permitAll)
                    authorize("/swagger-resources/**", permitAll)
                    authorize("/swagger-ui.html", permitAll)

                    // Admin endpoints
                    authorize("$prefix/admin/user/**", hasAuthority(Role.ROLE_ADMIN.authority))
                    authorize("$prefix/admin/role/**", hasAuthority(Role.ROLE_ADMIN.authority))

                    // Worker management
                    authorize("$prefix/workers/management/**", hasAuthority(Role.ROLE_OWNER.authority))

                    // Categories
                    authorize(HttpMethod.POST, "$prefix/categories", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.ADD_CATEGORY.permissionname()
                    ))
                    authorize(HttpMethod.GET, "$prefix/categories", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.GET_CATEGORY.permissionname()
                    ))
                    authorize(HttpMethod.PATCH, "$prefix/categories/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.UPDATE_CATEGORY.permissionname()
                    ))
                    authorize(HttpMethod.DELETE, "$prefix/categories/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.REMOVE_CATEGORY.permissionname()
                    ))

                    // Products
                    authorize(HttpMethod.POST, "$prefix/products", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.ADD_PRODUCT.permissionname()
                    ))
                    authorize(HttpMethod.GET, "$prefix/products/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.GET_PRODUCT.permissionname()
                    ))
                    authorize(HttpMethod.PATCH, "$prefix/products/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.UPDATE_PRODUCT.permissionname()
                    ))
                    authorize(HttpMethod.DELETE, "$prefix/products/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.REMOVE_PRODUCT.permissionname()
                    ))

                    // Customers
                    authorize(HttpMethod.POST, "$prefix/customers/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.ADD_CUSTOMERS.permissionname()
                    ))
                    authorize(HttpMethod.GET, "$prefix/customers/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.GET_CUSTOMERS.permissionname()
                    ))
                    authorize(HttpMethod.DELETE, "$prefix/customers/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.REMOVE_CUSTOMERS.permissionname()
                    ))

                    // Orders
                    authorize(HttpMethod.POST, "$prefix/orders", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.ADD_ORDER.permissionname()
                    ))
                    authorize(HttpMethod.GET, "$prefix/orders/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.GET_ORDER.permissionname()
                    ))
                    authorize(HttpMethod.PUT, "$prefix/orders/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.UPDATE_ORDER.permissionname()
                    ))
                    authorize(HttpMethod.PATCH, "$prefix/orders/cancel/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.CANCEL_ORDER.permissionname()
                    ))
                    authorize(HttpMethod.PATCH, "$prefix/orders/accept/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.UPDATE_ORDER.permissionname() // accept sipariş için update yetkisi
                    ))
                    authorize(HttpMethod.DELETE, "$prefix/orders/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.DELETE_ORDER.permissionname()
                    ))

                    // Worker management endpoints
                    authorize(HttpMethod.POST, "$prefix/workers/management", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.ADD_WORKER.permissionname()
                    ))
                    authorize(HttpMethod.GET, "$prefix/workers/management", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.GET_WORKER.permissionname()
                    ))
                    authorize(HttpMethod.GET, "$prefix/workers/management/deleted/**", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.GET_WORKER.permissionname()
                    ))
                    authorize(HttpMethod.PATCH, "$prefix/workers/management/{workerId}/restore", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.UPDATE_WORKER.permissionname()
                    ))
                    authorize(HttpMethod.PATCH, "$prefix/workers/management/{userId}/worker-permissions", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.UPDATE_WORKER.permissionname()
                    ))
                    authorize(HttpMethod.DELETE, "$prefix/workers/management/{workerId}", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.REMOVE_WORKER.permissionname()
                    ))
                    authorize(HttpMethod.GET, "$prefix/workers/management/worker-permissions", hasAnyAuthority(
                        Role.ROLE_OWNER.authority,
                        WorkerPermissions.GET_WORKER.permissionname()
                    ))

                    authorize(anyRequest, authenticated)
                }

                // Filters
                http.addFilterBefore(tenantFilter, UsernamePasswordAuthenticationFilter::class.java)
                http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            }
            return http.build()
        }
    }
