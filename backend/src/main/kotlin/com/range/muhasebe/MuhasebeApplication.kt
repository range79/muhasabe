package com.range.muhasebe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
class MuhasebeApplication

fun main(args: Array<String>) {
    runApplication<MuhasebeApplication>(*args)
}
