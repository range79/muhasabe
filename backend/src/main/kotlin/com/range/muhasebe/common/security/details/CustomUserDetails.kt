package com.range.muhasebe.common.security.details


import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.worker.domain.model.Worker
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(private val user: User,
    private val worker: Worker?) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority?> {
        val authorities = mutableListOf<GrantedAuthority>()
        authorities.add(user.role)
        worker?.permissions?.let { perm ->
            authorities.add(GrantedAuthority { "PERM_${perm.name}" })
        }
        return authorities

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