package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.config.security.LoginUser
import com.cultureshock.madeleine.config.web.resolver.AuthenticationIdResolver
import com.cultureshock.madeleine.exception.ApiUnauthrizedException
import com.cultureshock.madeleine.rest.RestSupport
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.slot
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.MethodParameter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.filter.CharacterEncodingFilter
import support.TestEnvironment
import support.createUser


@TestEnvironment
abstract class RestControllerTest(): RestSupport() {
    @MockkBean
    private lateinit var loginUserResolver: AuthenticationIdResolver

    lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setUp(
        webApplicationContext: WebApplicationContext
    ) {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext!!)
            .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .build()

        loginUserResolver.also {
            slot<MethodParameter>().also { slot ->
                every { it.supportsParameter(capture(slot)) } answers {
                    slot.captured.hasParameterAnnotation(LoginUser::class.java)
                }
            }
            slot<NativeWebRequest>().also { slot ->
                every { it.resolveArgument(any(), any(), capture(slot), any()) } answers {

                    val requestHeader = slot.captured.getHeader("user_id")?.toLongOrNull()

                    if(requestHeader !=null){
                        throw ApiUnauthrizedException()
                    }
                    createUser()
                }
            }
        }
    }
}