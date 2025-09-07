package com.range.muhasebe.common.security.details


import com.range.muhasebe.userManagement.user.domain.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(private val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority?> {
        return mutableListOf(user.role)
    }

    override fun getPassword(): String {
        return user.password
    }


    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
    fun getId(): Long {
        return user.id!!
    }
}