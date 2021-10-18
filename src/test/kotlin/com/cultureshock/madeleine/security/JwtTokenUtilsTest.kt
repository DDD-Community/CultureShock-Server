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
import support.USER_NAME
import support.UnitTest
import support.createUser
import support.createUserDetails

private const val NOT_VALID_TOKEN = ""
private const val NEGATIVE_VALIDITY_TIME = -10L
private const val SECRET_KEY = "TESTKEY"
private const val VALIDITY_TIME = 100L

@UnitTest
internal class JwtTokenUtilsTest {
    @MockK
    private lateinit var userRepository: UserRepository

    @MockK
    private lateinit var userDetailsService: UserDetailsService
    private lateinit var userDetails: UserDetails

    @BeforeEach
    internal fun setUp(){
        every{ userRepository.findByUsernameAndActive(USER_NAME,true)} answers { createUser() }
        every{ userDetailsService.loadUserByUsername(USER_NAME) } answers { createUserDetails() }
        userDetails = userDetailsService.loadUserByUsername(USER_NAME)
    }

    @Test
    fun `payload를 넣어 토큰을 생성하고 토큰에서 다시 userDetails 값을 불러올 수 있는지 확인한다`() {
        val jwtTokenUtils = JwtTokenUtils(secretKey = SECRET_KEY, expiration = VALIDITY_TIME)
        val token = jwtTokenUtils.generateToken(userDetails = userDetails)

        assertThat(jwtTokenUtils.getUsernameFromToken(token = token)).isEqualTo(USER_NAME)
    }

    @Test
    fun `유효시간이 지난 토큰의 유효성 검사가 실패한다`() {
        val jwtTokenUtils = JwtTokenUtils(secretKey = SECRET_KEY, expiration = NEGATIVE_VALIDITY_TIME)
        val token = jwtTokenUtils.generateToken(userDetails = userDetails)

        assertThat(jwtTokenUtils.validateToken(token = token, userDetails = userDetails)).isFalse()
    }

    @Test
    fun `올바르지 않은 토큰의 유효성 검사가 실패한다`() {
        val jwtTokenUtils = JwtTokenUtils(secretKey = SECRET_KEY, expiration = VALIDITY_TIME)

        assertThat(jwtTokenUtils.validateToken(token = NOT_VALID_TOKEN, userDetails = userDetails)).isFalse()
    }
}