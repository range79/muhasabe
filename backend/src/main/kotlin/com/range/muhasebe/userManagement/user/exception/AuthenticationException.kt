package com.eloboostum.usermanagement.user.exception

import com.range.muhasebe.common.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class AuthenticationException(msg: String): AbstractExceptionHandler(msg, HttpStatus.BAD_REQUEST) {
}