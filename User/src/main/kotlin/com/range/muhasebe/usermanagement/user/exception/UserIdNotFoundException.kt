package com.eloboostum.usermanagement.user.exception

import com.range.muhasebe.usermanagement.common.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class UserIdNotFoundException (msg: String?): AbstractExceptionHandler(msg?:"User id Not found", HttpStatus.NOT_FOUND)