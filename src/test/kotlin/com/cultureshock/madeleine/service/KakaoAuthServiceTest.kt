package com.cultureshock.madeleine.service

import com.cultureshock.madeleine.domain.user.AuthorityRepository
import com.cultureshock.madeleine.domain.user.KakaoUserRepository
import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.domain.user.enum.AuthorityName
import com.cultureshock.madeleine.rest.dto.request.SignInRequest
import com.cultureshock.madeleine.rest.dto.response.SignInResponse
import com.cultureshock.madeleine.rest.dto.response.UserResponse
import com.cultureshock.madeleine.service.user.KakaoAuthService
import com.cultureshock.madeleine.service.user.UserAuthService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.password.PasswordEncoder
import support.*

@UnitTest
internal class KakaoAuthServiceTest {
    @MockK
    private lateinit var userRepository: UserRepository

    @MockK
    private lateinit var kakaoUserRepository: KakaoUserRepository

    @MockK
    private lateinit var authorityRepository: AuthorityRepository

    private lateinit var passwordEncoder: PasswordEncoder
    private lateinit var userAuthService: UserAuthService
    private lateinit var kakaoAuthService: KakaoAuthService

    @BeforeEach
    internal fun setUp() {
        every { userRepository.findByEmail(any()) } answers  { createUser()}
        passwordEncoder = createPasswordEncoder()
        userAuthService = UserAuthService(userRepository = userRepository, authorityRepository = authorityRepository, passwordEncoder = passwordEncoder, kakaoUserRepository = kakaoUserRepository)
        kakaoAuthService = KakaoAuthService(userAuthService = userAuthService, userRepository = userRepository)
    }

    @DisplayName("인증 후 회원가입")
    @Nested
    inner class GenerateToken {
        private lateinit var response: UserResponse

        fun subject(): SignInResponse {
            every{ userRepository.save(any())} answers { createUser() }
            return kakaoAuthService.kakaoSignIn(createSignInRequest())
        }

        @Test
        fun `회원가입을 진행한다`() {
            response = createUserResponse()
            Assertions.assertThat(subject().user_id).isEqualTo(response.id)
        }

    }
}