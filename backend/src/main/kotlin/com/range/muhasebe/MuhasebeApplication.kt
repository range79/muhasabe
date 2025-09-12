package com.range.muhasebe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
class MuhasebeApplication

fun main(args: Array<String>) {
    runApplication<MuhasebeApplication>(*args)
}
