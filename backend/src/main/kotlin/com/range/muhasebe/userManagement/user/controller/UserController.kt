package com.range.muhasebe.userManagement.user.controller

import com.range.muhasebe.userManagement.user.api.UserApi
import com.range.muhasebe.userManagement.user.dto.MyInfo
import com.range.muhasebe.userManagement.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
): UserApi {

    override fun me(): MyInfo {
        return userService.me()
    }
}