package com.eloboostum.usermanagement.user.exception

import com.range.muhasebe.usermanagement.common.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class UserNotFoundException(msg: String?): AbstractExceptionHandler(msg?:"User Not Found", HttpStatus.NOT_FOUND) {
}