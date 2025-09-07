package com.range.muhasebe.common.security

import com.range.muhasebe.common.security.jwt.JWTFilter
import com.range.muhasebe.userManagement.user.domain.model.Role

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(private val jwtFilter: JWTFilter) {
    @Value("\${api.prefix}")
    private lateinit var prefix: String;
    private val adminList: List<String> = listOf(
        Role.ROLE_ADMIN.authority,
    )


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http{
            csrf { disable() }
            authorizeHttpRequests {
                //login and register
                authorize("$prefix/auth/**",permitAll)

                //all users can react this one
                authorize ("$prefix/service/**",permitAll)
                //swagger stuff
                authorize ("/v3/**",permitAll)
                authorize("/swagger-ui/**",permitAll)
                authorize("/swagger-resources/**",permitAll)
                authorize ("/swagger-ui.html",permitAll)

                authorize("$prefix/errors/**",hasAnyAuthority(*adminList.toTypedArray()))
                authorize ("$prefix/admin/user/**",hasAnyAuthority(*adminList.toTypedArray()))
                authorize ("$prefix/service/create",hasAnyAuthority(*adminList.toTypedArray()))
                authorize ("$prefix/service/",hasAnyAuthority(*adminList.toTypedArray()))


                authorize(anyRequest, authenticated)
            }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(jwtFilter)


        }
        return http.build()
    }



}