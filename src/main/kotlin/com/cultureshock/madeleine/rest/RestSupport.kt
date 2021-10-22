package com.cultureshock.madeleine.rest

import com.cultureshock.madeleine.rest.dto.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.nio.charset.StandardCharsets

val MEDIA_TYPE_APPLICATION_JSON_UTF8 = MediaType("application", "json", StandardCharsets.UTF_8)
const val MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8"

abstract class RestSupport {
    protected open fun <T> response(data: T): ResponseEntity<Any> {
        return ResponseEntity
            .ok()
            .contentType(MEDIA_TYPE_APPLICATION_JSON_UTF8)
            .body(Response(code = "200", data = data, message = "ok"))
    }

    protected open fun unauthorized(message: String): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(Response(code = "401", data = null, message = message))
    }

    protected open fun response_400(message: String): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MEDIA_TYPE_APPLICATION_JSON_UTF8)
            .body(Response(code = "400", data = null, message = message))
    }

    protected open fun response_500(message: String): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .contentType(MEDIA_TYPE_APPLICATION_JSON_UTF8)
            .body(Response(code = "500", data = null, message = message))
    }
}