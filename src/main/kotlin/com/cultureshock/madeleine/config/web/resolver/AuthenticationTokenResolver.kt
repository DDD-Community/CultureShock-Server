package com.cultureshock.madeleine.config.web.resolver

import com.cultureshock.madeleine.auth.security.JwtTokenUtils
import com.cultureshock.madeleine.config.web.dto.AuthenticatedUser_v1
import com.cultureshock.madeleine.domain.user.KakaoUserRepository
import com.cultureshock.madeleine.domain.user.UserRepository
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class AuthenticationTokenResolver(
    private val header: String,
    private val jwtTokenUtils: JwtTokenUtils,
    private val userRepository: UserRepository,
    private val kakaoUserRepository: KakaoUserRepository
): HandlerMethodArgumentResolver {

    override fun supportsParameter(
        parameter: MethodParameter
    ): Boolean {
        return parameter.parameterType == AuthenticatedUser_v1::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val token = webRequest.getToken()
        val username = jwtTokenUtils.getUsernameFromToken(token) ?: return null
        val user = kakaoUserRepository.findByUsernameAndActive(username) ?: return null

        return AuthenticatedUser_v1(uid = user.id, username = user.username, token = token)
    }

    private fun NativeWebRequest.getToken(
    ): String {
        return getHeader(header)?.substring(7) ?: ""
    }
}