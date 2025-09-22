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
        http{
            csrf { disable() }
            authorizeHttpRequests {
                //login and register
                authorize("$prefix/auth/**",permitAll)


                //swagger stuff
                authorize ("/v3/**",permitAll)
                authorize("/swagger-ui/**",permitAll)
                authorize("/swagger-resources/**",permitAll)
                authorize ("/swagger-ui.html",permitAll)


                authorize ("$prefix/admin/user/**",hasAuthority(Role.ROLE_ADMIN.authority))
                authorize ( "${prefix}/admin/roles/**",hasAuthority(Role.ROLE_ADMIN.authority) )

                authorize("$prefix/workers/management",hasAuthority(Role.ROLE_OWNER.authority))

                //Categories
                authorize (HttpMethod.POST,"${prefix}/categories",hasAnyAuthority( Role.ROLE_OWNER.authority, WorkerPermissions.ADD_CATEGORY.permissionname()))
                authorize (HttpMethod.GET,"${prefix}/categories",hasAnyAuthority( Role.ROLE_OWNER.authority, WorkerPermissions.GET_CATEGORY.permissionname()))





                //Customer
                authorize (HttpMethod.GET,"${prefix}/customers/**",
                    hasAnyAuthority(Role.ROLE_OWNER.authority, WorkerPermissions.GET_CUSTOMERS.permissionname()))
                authorize(HttpMethod.DELETE,"${prefix}/customers/**",
                    hasAnyAuthority(Role.ROLE_OWNER.authority, WorkerPermissions.REMOVE_CUSTOMERS.permissionname()))
                authorize(HttpMethod.POST,
                    "${prefix}/customers/**",hasAnyAuthority(Role.ROLE_OWNER.authority,
                        WorkerPermissions.ADD_CUSTOMERS.permissionname())  )

                //Product
                authorize(HttpMethod.PATCH,"${prefix}/products/**",hasAnyAuthority( Role.ROLE_OWNER.authority,WorkerPermissions.UPDATE_PRODUCT.permissionname()))
                authorize(HttpMethod.POST,"${prefix}/products",hasAnyAuthority( Role.ROLE_OWNER.authority,WorkerPermissions.ADD_PRODUCT.permissionname()))
                authorize(HttpMethod.GET,"${prefix}/products/**",hasAnyAuthority( Role.ROLE_OWNER.authority))
                authorize (HttpMethod.DELETE,"${prefix}/products/**",hasAnyAuthority(Role.ROLE_OWNER.authority,
                    WorkerPermissions.REMOVE_PRODUCT.permissionname()))

//ORDER



                authorize(anyRequest, authenticated)
            }
            http.addFilterBefore(tenantFilter, UsernamePasswordAuthenticationFilter::class.java)
            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)


        }
        return http.build()
    }



}