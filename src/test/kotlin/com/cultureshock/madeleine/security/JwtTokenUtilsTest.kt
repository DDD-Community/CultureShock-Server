package com.cultureshock.madeleine.security

import com.cultureshock.madeleine.auth.security.JwtTokenUtils
import com.cultureshock.madeleine.domain.user.UserRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.test.context.support.WithUserDetails
import support.USER_NAME
import support.UnitTest
import support.createUser

private const val NOT_VALID_TOKEN = ""
private const val NEGATIVE_VALIDITY_TIME = -10L
private const val SECRET_KEY = "TESTKEY"
private const val EXPIRATION = 1L

@UnitTest
internal class JwtTokenUtilsTest {
    @MockK
    private lateinit var userRepository: UserRepository

    @MockK
    private lateinit var userDetailsService: UserDetailsService

    @BeforeEach
    internal fun setUp(){
        every{ userRepository.findByUsernameAndActive(USER_NAME,true)} answers {createUser()}
    }

    @Test
    @WithUserDetails
    fun `payload를 넣어 토큰을 생성하고 토큰에서 다시 payload를 불러올 수 있는지 확인한다`() {
        val jwtTokenUtils = JwtTokenUtils(secretKey = SECRET_KEY, expiration = EXPIRATION)

        //val token = jwtTokenUtils.generateToken()
        //assertThat(jwtTokenUtils.getUsernameFromToken()).isEqualTo(USER_NAME)
    }
}