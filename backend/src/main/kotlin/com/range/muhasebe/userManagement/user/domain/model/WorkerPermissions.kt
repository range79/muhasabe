package com.range.muhasebe.userManagement.user.domain.model

enum class WorkerPermissions {
    //CATEGORY
    GET_CATEGORY,
    ADD_CATEGORY,
    UPDATE_CATEGORY,
    REMOVE_CATEGORY,
    //product
    GET_PRODUCT,
    REMOVE_PRODUCT,
    UPDATE_PRODUCT,
    ADD_PRODUCT;
    //USER MANAGEMENT

    fun permissionname(): String{
        return "PERM_${name}"
    }
}