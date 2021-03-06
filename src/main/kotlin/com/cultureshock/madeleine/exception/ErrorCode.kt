package com.cultureshock.madeleine.exception

import org.springframework.http.HttpStatus
import java.util.*

enum class ErrorCode(
    val status: HttpStatus,
    var message: String
) {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버와의 통신이 원할하지 않습니다"),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "매칭되는 ID값이 존재하지 않습니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "invalid type value"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not found"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Method type is invalid"),
    EXIST_USER(HttpStatus.BAD_REQUEST, "이미 가입한 유저입니다."),
    NOT_FOUND_USER(HttpStatus.INTERNAL_SERVER_ERROR, "사용자를 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없는 사용자입니다."),
    UNAUTHORIZED_KAKAO(HttpStatus.UNAUTHORIZED, "카카오 로그인에 실패하였습니다.")
}

data class ErrorsDetails(val code: Int?, val timezone: Long, val message: String)