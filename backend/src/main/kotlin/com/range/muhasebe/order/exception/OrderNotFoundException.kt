package com.range.muhasebe.order.exception

import com.range.muhasebe.common.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class OrderNotFoundException(message: String): AbstractExceptionHandler(msg = message, HttpStatus.NOT_FOUND) {
}