package com.cultureshock.madeleine.exception

open class UserApiException(
    code: ErrorCode,
    override val message: String? = null
) : RuntimeException(
    message ?: code.message
)

class ApiExistUserException: UserApiException(ErrorCode.EXIST_USER)
class ApiNotFoundUserException: UserApiException(ErrorCode.NOT_FOUND_USER)