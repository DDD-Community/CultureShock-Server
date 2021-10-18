package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.MadeleineApplication
import com.cultureshock.madeleine.auth.security.JwtTokenUtils
import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.rest.dto.request.SignInRequest
import com.cultureshock.madeleine.service.user.KakaoAuthService
import com.cultureshock.madeleine.service.user.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import support.TestEnvironment


private const val VALID_TOKEN = "SOME_VALID_TOKEN"
private const val RANDOM_PASSWORD = "nEw_p@ssw0rd"
private const val PASSWORD = "password"
private const val INVALID_PASSWORD = "invalid_password"

@WebMvcTest(
    controllers = [UserController::class, SignInController::class],
    includeFilters = [
        ComponentScan.Filter(type = FilterType.REGEX, pattern = ["com.cultureshock.madeleine.security.*"]),
        ComponentScan.Filter(type = FilterType.REGEX, pattern = ["com.cultureshock.madeleine.config.*"])
    ]
)
@TestEnvironment
class UserControllerTest(
    private val objectMapper: ObjectMapper
) {
    @MockBean
    private lateinit var kakaoAuthService: KakaoAuthService

    @MockBean
    private lateinit var userService: UserService

    @MockBean
    private lateinit var jwtTokenUtils: JwtTokenUtils

    private lateinit var mockMvc: MockMvc

    companion object {
        private val log: Logger = LoggerFactory.getLogger(SignInController::class.java)
    }

    @BeforeEach
    internal fun setUp(webApplicationContext: WebApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .build()
    }
    
    /*
    @Test
    @WithMockUser
    @Throws(Exception::class)
    fun `유저 정보를 불러온다`(){

        this.mvc!!
            .perform(MockMvcRequestBuilders.get("/api/v1/user").header("").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
    }
    */
}