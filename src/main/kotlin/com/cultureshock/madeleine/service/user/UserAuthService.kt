package com.cultureshock.madeleine.service.user

import com.cultureshock.madeleine.auth.client.kakao.dto.response.KakaoUserResponse
import com.cultureshock.madeleine.common.util.KakaoAccountUtils
import com.cultureshock.madeleine.domain.user.*
import com.cultureshock.madeleine.domain.user.enum.AuthorityName
import com.cultureshock.madeleine.domain.user.enum.SocialType
import com.cultureshock.madeleine.rest.dto.response.UserResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import retrofit2.Response

@Service
class UserAuthService(
    private val userRepository: UserRepository,
    private val kakaoUserRepository: KakaoUserRepository,
    private val authorityRepository: AuthorityRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun joinUser(response: UserResponse) : User{
        return createUser(
            response.nickname,
            response.email
        )
    }

    @Transactional
    fun createUser(nickname: String, email: String): User{

        val user = User(
            email = email,
            nickname = nickname
        )
        return userRepository.save(user)
    }

    fun kakaoJoin(response: KakaoUserResponse): KakaoUser {
        return createUser_v1(
            providerId = response.id,
            email = response.kakaoAccount.email,
            nickname = response.properties.nickname,
            profileImage = response.properties.profileImage,
            socialType = SocialType.KAKAO
        )
    }

    @Transactional
    fun createUser_v1(providerId: Long, email: String, profileImage: String, nickname: String, socialType: SocialType): KakaoUser {
        val authorities = authorityRepository.findByAuthorityName(AuthorityName.ROLE_LEVEL0)

        val user = KakaoUser(
            providerId = providerId.toString(),
            email = email,
            username = KakaoAccountUtils.getUsernameByKakaoAccount(providerId),
            password = passwordEncoder.encode(KakaoAccountUtils.getPasswordByKakaoAccount(providerId)),
            profileImage = profileImage,
            nickname = nickname,
            socialType = SocialType.KAKAO,
            authorities = listOf(authorities)
        )

        return kakaoUserRepository.save(user)
    }
}