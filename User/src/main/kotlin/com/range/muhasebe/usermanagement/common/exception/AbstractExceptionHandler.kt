package com.range.muhasebe.usermanagement.common.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

open class AbstractExceptionHandler(msg: String, val status: HttpStatus) : RuntimeException (msg){
}