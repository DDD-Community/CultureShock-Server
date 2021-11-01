package com.cultureshock.madeleine.exception

import com.cultureshock.madeleine.common.util.toSeoulEpochSecond
import java.time.LocalDateTime

open class TicketException(
    val code: ErrorCode,
    val timestamp: Long = LocalDateTime.now().toSeoulEpochSecond()!!,
    override val message: String? = null
) : Exception()

class ArguExistTicketException: TicketException(code = ErrorCode.INVALID_INPUT_VALUE)
