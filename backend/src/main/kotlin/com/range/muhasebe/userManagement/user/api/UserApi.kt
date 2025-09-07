package com.range.muhasebe.userManagement.user.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("\${api.prefix}/profile")
interface UserApi {
    @GetMapping("/me")
    fun me ()
}