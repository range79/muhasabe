package com.range.muhasebe.userManagement.user.domain.model

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority{
// The "user" role is the basic role of the application and has standard user permissions.
// These users can typically view their own account information and use the general features of the application.
//
// The "owner" role is designed for business or store owners.
// Owners can manage their workers and employees, create different categories, and add or manage products or services within these categories.
// They can also track product details and stock status.
// Owners can assign specific permissions to their workers, allowing them to only adjust product quantities.
//
// The "worker" role has limited permissions defined by the owner.
// These users can only update product quantities and do not have permissions to add or remove products or manage categories.
//
// The "admin" role has the highest level of permissions in the application.
// Admins can manage all users, ban users if necessary, change roles, and handle general application administration tasks.
// They are also responsible for maintaining system integrity and managing permission controls.
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_OWNER,
    ROLE_WORKER;


    override fun getAuthority(): String {
       return name
    }
}