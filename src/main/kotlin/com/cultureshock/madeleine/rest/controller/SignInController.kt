package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.rest.dto.request.SignInRequest
import com.cultureshock.madeleine.rest.dto.request.SignOutRequest
import com.cultureshock.madeleine.service.user.KakaoAuthService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.servlet.http.HttpServletRequest

@Api(tags = ["권한"])
@RestController
@RequestMapping("/api/v1/auth")
class SignInController(
    @Value("\${kakao.client_id}") private val clientId: String,
    private val kakaoAuthService: KakaoAuthService
): RestSupport() {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(SignInController::class.java)
    }

    @GetMapping("/kakao/signin")
    @ApiOperation(value="카카오 로그인.GET", notes="[GET]카카오 로그인 시도 => 회원이 아니면 회원가입까지 진행 => callback")
    fun kakaoBackendSignPage(): ResponseEntity<*> {
        val redirectUrl = "https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=http://localhost:8080/api/v1/auth/kakao/signin/callback&response_type=code"
        val uri = URI(redirectUrl)
        val headers = HttpHeaders()
        headers.location = uri
        return ResponseEntity<Any>(headers, HttpStatus.SEE_OTHER)
    }

    @PostMapping("/kakao/signin")
    @ApiOperation(value="카카오 로그인.POST", notes="[POST]카카오 로그인")
    fun kakaoSignIn(@RequestBody request: SignInRequest): ResponseEntity<Any> {
        return response(kakaoAuthService.kakaoSignIn(request))
    }

    @GetMapping("/kakao/signin/callback")
    @ApiOperation(value="카카오 로그인.GET CallBack", notes="[GET]카카오 로그인 callback")
    fun kakaoBackendSigninCallback(request: HttpServletRequest, @RequestParam("code") code: String): ResponseEntity<*> {
        return response(kakaoAuthService.kakaoSignIn(SignInRequest(code = code, redirectUri = "http://localhost:8080/api/v1/auth/kakao/signin/callback", agreementMarketingRecv = true)))
    }

    @PostMapping("/signout")
    @ApiOperation(value="카카오 인증 토큰 해제", notes="")
    fun kakaoSignOut(@RequestBody request: SignOutRequest): ResponseEntity<Any>{
        //회원 정보도 삭제할것
        return response(kakaoAuthService.kakaoSignOut(request))
    }


}