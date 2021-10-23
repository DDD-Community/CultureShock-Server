package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.MadeleineApplication
import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.rest.RestSupport

import com.cultureshock.madeleine.rest.dto.response.UserResponse
import com.cultureshock.madeleine.service.user.KakaoAuthService
import com.cultureshock.madeleine.service.user.UserService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import support.createUser
import support.createUserResponse

private const val VALID_TOKEN = "SOME_VALID_TOKEN"
private const val RANDOM_PASSWORD = "nEw_p@ssw0rd"
private const val PASSWORD = "password"
private const val INVALID_PASSWORD = "invalid_password"

@SpringBootTest(classes = [MadeleineApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserControllerTest(
): RestControllerTest() {

    @MockkBean
    private lateinit var kakaoAuthService: KakaoAuthService

    @MockkBean
    private lateinit var userService: UserService

    @MockkBean
    private lateinit var userRepository: UserRepository

    @Autowired
    private val wac: WebApplicationContext? = null

    companion object {
        private val log: Logger = LoggerFactory.getLogger(UserController::class.java)
    }

    private val userResponse = createUserResponse()

    @Test
    fun `기존 사용자 정보와 일치하는 사용자 검증 요청에 응답으로 Authorized를 반환한다`() {
        every { userService.getUserInfo(any()) } returns userResponse
        every { userRepository.getById(any()) } returns createUser()

        mockMvc.get("/api/v1/user") {
            header("user_id",1)
        }.andExpect {
            status { ok() }
            content {
                MockMvcResultMatchers.jsonPath("code").value("401")
                MockMvcResultMatchers.jsonPath("message").value("권한이 없는 사용자입니다.")
            }
        }
    }

    @Test
    fun `기존 사용자 정보와 일치하지 않는 사용자 검증 요청에 응답으로 unAuthorized를 반환한다`() {
        every { userService.getUserInfo(any()) } returns userResponse

        mockMvc.get("/api/v1/user") {
            header("user_id",2)
        }.andExpect {
            status { ok() }
            content {
                MockMvcResultMatchers.jsonPath("code").value("401")
                MockMvcResultMatchers.jsonPath("message").value("권한이 없는 사용자입니다.")
            }
        }
    }

}
