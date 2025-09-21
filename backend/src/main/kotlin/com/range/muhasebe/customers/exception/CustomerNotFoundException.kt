package com.range.muhasebe.customers.exception

import com.range.muhasebe.common.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class CustomerNotFoundException(msg: String?): AbstractExceptionHandler(msg?:"Customer Not Found", status = HttpStatus.NOT_FOUND)