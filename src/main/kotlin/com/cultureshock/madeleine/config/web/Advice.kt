package com.cultureshock.madeleine.config.web

import com.cultureshock.madeleine.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [ApiUnauthrizedException::class, ApiExistUserException::class])
    fun handleApiNotFoundUser(ex: ApiUnauthrizedException,request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(
            ex.code.status.toString().substring(0,3).toIntOrNull(),
            ex.timestamp,
            ex.code.message
        )
        return ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(value = [ArguExistPerformanceException::class, ArguExistTicketException::class])
    fun handleIdNotFound(ex: ArguExistTicketException, request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(
            ex.code.status.toString().substring(0,3).toIntOrNull(),
            ex.timestamp,
            ex.code.message
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}