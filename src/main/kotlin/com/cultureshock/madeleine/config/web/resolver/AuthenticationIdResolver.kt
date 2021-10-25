package com.cultureshock.madeleine.config.web.resolver

import com.cultureshock.madeleine.config.security.LoginUser
import com.cultureshock.madeleine.config.web.dto.AuthenticatedUser
import com.cultureshock.madeleine.domain.user.*
import com.cultureshock.madeleine.exception.ApiNotFoundUserException
import com.cultureshock.madeleine.exception.ApiUnauthrizedException
import com.cultureshock.madeleine.exception.ArguExistPerformanceException
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest


class AuthenticationIdResolver(
    private val header: String,
    private val userRepository: UserRepository
): HandlerMethodArgumentResolver {

    override fun supportsParameter(
        parameter: MethodParameter
    ): Boolean {
        return parameter.hasParameterAnnotation(LoginUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {

        val request: HttpServletRequest = webRequest.getNativeRequest() as HttpServletRequest
        val user: User
        try {
            val requestHeader: Long= request.getHeader(header).toLong()
            user = userRepository.findById(requestHeader).get()
        } catch (e: Exception){
            throw ApiUnauthrizedException()
        }
        return AuthenticatedUser(id = user.id, nickname = user.nickname, email = user.email)
    }
}