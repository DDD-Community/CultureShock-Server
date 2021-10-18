package com.cultureshock.madeleine.service

import com.cultureshock.madeleine.auth.client.kakao.dto.response.KakaoUserResponse
import com.cultureshock.madeleine.auth.security.JwtTokenUtils
import com.cultureshock.madeleine.domain.user.AuthorityRepository
import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.domain.user.enum.AuthorityName
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
internal class UserAuthServiceTest {
    @MockK
    private lateinit var userRepository: UserRepository

    @MockK
    private lateinit var authorityRepository: AuthorityRepository

    private lateinit var passwordEncoder: PasswordEncoder
    private lateinit var userAuthService: UserAuthService

    @BeforeEach
    internal fun setUp() {
        every { authorityRepository.findByAuthorityName(AuthorityName.ROLE_LEVEL0)} returns createAuthority()
        passwordEncoder = createPasswordEncoder()
        userAuthService = UserAuthService(userRepository = userRepository, authorityRepository = authorityRepository, passwordEncoder = passwordEncoder)
    }
    
    @DisplayName("카카오 토큰 발급")
    @Nested
    inner class GenerateToken{
        private lateinit var response: KakaoUserResponse

        fun subject(): User {
            every{ userRepository.save(any())} answers { createUser() }
            return userAuthService.kakaoJoin(response)
        }

        @Test
        fun `인증에 성공하면 회원가입 DB에 저장한다`() {
            response = createKakaoUserResponse()
            Assertions.assertThat(subject().email).isEqualTo(EMAIL)
        }
    }
}