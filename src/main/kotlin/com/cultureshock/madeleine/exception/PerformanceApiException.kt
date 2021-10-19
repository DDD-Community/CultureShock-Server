package com.cultureshock.madeleine.exception

open class PerformanceApiException(
    code: ErrorCode,
    override val message: String? = null
) : RuntimeException(
    message ?: code.message
)

class ArguExistPerformanceException: PerformanceApiException(ErrorCode.INVALID_INPUT_VALUE, "매칭되는 공연ID가 존재하지 않습니다")