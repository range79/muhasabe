package com.range.muhasebe.userManagement.user.domain.model

enum class WorkerPermissions {
    // CATEGORY
    GET_CATEGORY,
    ADD_CATEGORY,
    UPDATE_CATEGORY,
    REMOVE_CATEGORY,

    // PRODUCT
    GET_PRODUCT,
    ADD_PRODUCT,
    UPDATE_PRODUCT,
    REMOVE_PRODUCT,

    // CUSTOMER
    GET_CUSTOMERS,
    ADD_CUSTOMERS,
    UPDATE_CUSTOMERS,
    REMOVE_CUSTOMERS,

    // ORDER
    GET_ORDER,
    ADD_ORDER,
    UPDATE_ORDER,
    CANCEL_ORDER,
    DELETE_ORDER,

    // USER / WORKER MANAGEMENT
    GET_WORKER,
    ADD_WORKER,
    UPDATE_WORKER,
    REMOVE_WORKER;

    fun permissionname(): String{
        return "PERM_${name}"
    }
}