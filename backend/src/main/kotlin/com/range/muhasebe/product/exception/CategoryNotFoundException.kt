package com.range.muhasebe.product.exception

import com.range.muhasebe.common.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class CategoryNotFoundException: AbstractExceptionHandler("Category Not Found", HttpStatus.NOT_FOUND) {
}