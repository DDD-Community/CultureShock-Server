package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.rest.dto.request.SignInRequest
import com.cultureshock.madeleine.service.user.KakaoAuthService
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["회원가입 및 로그인"])
@RestController
@RequestMapping("/api/v1/auth")
class SignInController(
    private val kakaoAuthService: KakaoAuthService
): RestSupport() {

    @PostMapping("/kakao/signin")
    fun kakaoSignIn(@RequestBody request: SignInRequest): ResponseEntity<Any> {
        return response(kakaoAuthService.kakaoSignIn(request))
    }
}