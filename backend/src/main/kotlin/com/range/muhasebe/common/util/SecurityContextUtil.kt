package com.range.muhasebe.common.util

import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.common.security.details.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SecurityContextUtil {
    fun getCurrentUserId(): UUID {
        val userDetails = SecurityContextHolder.getContext().authentication?.principal as? CustomUserDetails
        return userDetails?.getId()?:throw UserNotFoundException("User Not Found")
    }
}
