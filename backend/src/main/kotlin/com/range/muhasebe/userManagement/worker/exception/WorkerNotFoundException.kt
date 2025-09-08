package com.range.muhasebe.userManagement.worker.exception

import com.range.muhasebe.common.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class WorkerNotFoundException(msg: String?): AbstractExceptionHandler(msg?:"Worker Not Found", HttpStatus.NOT_FOUND) {
}