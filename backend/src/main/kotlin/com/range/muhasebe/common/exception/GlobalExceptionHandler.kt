package com.range.muhasebe.common.exception

import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(AbstractExceptionHandler::class)
    fun  handlerAbstractExceptionHandler(e: AbstractExceptionHandler){
        return
    }
}
