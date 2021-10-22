package com.cultureshock.madeleine.exception

import com.cultureshock.madeleine.common.util.toSeoulEpochSecond
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.LocalDateTime

open class UserApiException(
    val code: ErrorCode,
    val timestamp: Long = LocalDateTime.now().toSeoulEpochSecond()!!,
    override val message: String? = null
) : Exception()

class ApiUnauthrizedException: UserApiException(code = ErrorCode.UNAUTHORIZED)
class ApiExistUserException: UserApiException(code = ErrorCode.EXIST_USER)
class ApiNotFoundUserException: UserApiException(code = ErrorCode.NOT_FOUND_USER)