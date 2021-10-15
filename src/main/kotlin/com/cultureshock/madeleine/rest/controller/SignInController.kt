package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.rest.dto.request.SignInRequest
import com.cultureshock.madeleine.service.user.KakaoAuthService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.servlet.http.HttpServletRequest

@Api(tags = ["회원가입 및 로그인"])
@RestController
@RequestMapping("/api/v1/auth")
class SignInController(
    @Value("\${kakao.client_id}") private val clientId: String,
    private val kakaoAuthService: KakaoAuthService
): RestSupport() {

    @GetMapping("/kakao/signin")
    fun kakaoBackendSignPage(): ResponseEntity<*> {
        val redirectUrl = "https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=http://localhost:8080/api/v1/auth/kakao/signin/callback&response_type=code"
        val uri = URI(redirectUrl)
        val headers = HttpHeaders()
        headers.location = uri
        return ResponseEntity<Any>(headers, HttpStatus.SEE_OTHER)
    }

    @PostMapping("/kakao/signin")
    fun kakaoSignIn(@RequestBody request: SignInRequest): ResponseEntity<Any> {
        return response(kakaoAuthService.kakaoSignIn(request))
    }

    @GetMapping("/kakao/signin/callback")
    fun kakaoBackendSigninCallback(request: HttpServletRequest, @RequestParam("code") code: String): ResponseEntity<*> {
        return response(kakaoAuthService.kakaoSignIn(SignInRequest(code = code, redirectUri = "http://localhost:8080/api/v1/auth/kakao/signin/callback", agreementMarketingRecv = true)))
    }




}