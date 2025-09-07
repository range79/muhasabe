package com.range.muhasebe.common.exception

import org.springframework.http.HttpStatus

class RoleMismatchException(msg: String?): AbstractExceptionHandler(msg?:"Not have Role for this operation", HttpStatus.UNAUTHORIZED) {
}