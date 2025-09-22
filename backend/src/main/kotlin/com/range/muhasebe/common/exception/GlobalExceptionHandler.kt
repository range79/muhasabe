package com.range.muhasebe.common.exception

import com.range.muhasebe.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AbstractExceptionHandler::class)
    fun handleAbstractException(e: AbstractExceptionHandler): ErrorResponse {
        return ErrorResponse(
            status = e.status.toString(),
            message = e.message ?: "Unexpected error",
            timestamp = LocalDateTime.now()
        )
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(e: AccessDeniedException): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.FORBIDDEN.toString(),
            message = e.message ?: "Access denied",
            timestamp = LocalDateTime.now()
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(e: MethodArgumentNotValidException): ErrorResponse {
        val errorMsg = e.bindingResult.allErrors.joinToString { it.defaultMessage ?: "Invalid field" }
        return ErrorResponse(
            status = HttpStatus.BAD_REQUEST.toString(),
            message = errorMsg,
            timestamp = LocalDateTime.now()
        )
    }


    @ExceptionHandler(Exception::class)
    fun handleGeneralException(e: Exception): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.toString(),
            message = e.message ?: "Internal server error",
            timestamp = LocalDateTime.now()
        )
    }
}
