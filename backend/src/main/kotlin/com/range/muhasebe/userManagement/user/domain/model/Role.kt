package com.range.muhasebe.userManagement.user.domain.model

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority{
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_OWNER,
    ROLE_WORKER;


    override fun getAuthority(): String {
       return name
    }
}