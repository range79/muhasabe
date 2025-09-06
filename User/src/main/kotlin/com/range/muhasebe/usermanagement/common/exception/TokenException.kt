package com.range.muhasebe.usermanagement.common.exception

import org.springframework.http.HttpStatus

class TokenException(msg: String?): AbstractExceptionHandler(msg?:"Token error", HttpStatus.BAD_REQUEST) {
}