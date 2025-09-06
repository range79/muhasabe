package com.range.muhasebe.usermanagement.user.domain.model

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority{
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_MODERATOR;


    override fun getAuthority(): String {
       return name
    }
}