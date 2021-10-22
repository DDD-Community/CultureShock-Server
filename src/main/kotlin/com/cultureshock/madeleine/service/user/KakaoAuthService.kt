package com.cultureshock.madeleine.service.user

import com.cultureshock.madeleine.auth.client.kakao.KakaoAuthClient
import com.cultureshock.madeleine.auth.client.kakao.KakaoClient
import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.domain.user.enum.SocialType
import com.cultureshock.madeleine.exception.ErrorCode
import com.cultureshock.madeleine.exception.UserApiException
import com.cultureshock.madeleine.auth.security.JwtTokenUtils
import com.cultureshock.madeleine.common.util.KakaoAccountUtils
import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.rest.dto.request.SignInRequest
import com.cultureshock.madeleine.rest.dto.request.SignInRequest_v1
import com.cultureshock.madeleine.rest.dto.request.SignOutRequest
import com.cultureshock.madeleine.rest.dto.response.SignInResponse
import com.cultureshock.madeleine.rest.dto.response.SignInResponse_v1
import com.cultureshock.madeleine.rest.dto.response.SignOutResponse
import com.cultureshock.madeleine.rest.dto.response.UserResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class KakaoAuthService(
    //private val kakaoAuthClient: KakaoAuthClient,
    //private val kakaoClient: KakaoClient,
    private val userAuthService: UserAuthService,
    //private val userService: UserService,
    private val userRepository: UserRepository,
    // private val userDetailsService: UserDetailsService,
    //private val authenticationManager: AuthenticationManager,
    //private val jwtTokenUtils: JwtTokenUtils
) {

    /**
     * @title 회원가입 or 로그인
     * @param SignInRequest
     * @return SignInResponse
     */
    fun kakaoSignIn(request: SignInRequest): SignInResponse {
        val user = userRepository.findByEmail(request.email)
            ?:userAuthService.joinUser(UserResponse.of(
                user = User(nickname = request.nickname, email = request.email)
            ))

        return SignInResponse(
            user_id = user.id
        )
    }



    /**
     * @deprecated v0.1
     *
     * @title 회원가입 or 로그인
     * @param SignInRequest
     * @return SignInResponse

    fun kakaoSignIn_v1(request: SignInRequest_v1): SignInResponse_v1 {
        val tokenResult = kakaoAuthClient.token(redirectUri = request.redirectUri, code = request.code)

        if (!tokenResult.isSuccessful) throw UserApiException(ErrorCode.UNAUTHORIZED_KAKAO, "카카오 토큰 조회 에러")

        val res = kakaoClient.getUserInfo(tokenResult.body()!!.accessToken)

        if (!res.isSuccessful) throw UserApiException(ErrorCode.UNAUTHORIZED_KAKAO, "카카오 유저 정보 조회 에러")
        val kakaoRes = res.body()!!
        // 회원가입 여부 확인 후, null일 경우 회원가입
        val user = userRepository.findByProviderIdAndSocialTypeAndActive(providerId = kakaoRes.id.toString(), socialType = SocialType.KAKAO)
            ?: userAuthService.kakaoJoin(kakaoRes)

        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(KakaoAccountUtils.getUsernameByKakaoAccount(kakaoRes.id), KakaoAccountUtils.getPasswordByKakaoAccount(kakaoRes.id)))
        val userDetails = userDetailsService.loadUserByUsername(user.username)
        SecurityContextHolder.getContext().authentication = authentication

        return SignInResponse_v1(
            nickname = user.nickname,
            email = user.email,
            socialType = user.socialType,
            token = jwtTokenUtils.generateToken(userDetails)
        )
    }
    */
    /**
     * @deprecated v0.1
     *
     * @title 카카오 로그인해제
     * @param SignInRequest
     * @return SignInResponse

    fun kakaoSignOut(request: SignOutRequest): SignOutResponse {
        val res = kakaoClient.signOutKakao(request.token)
        if (!res.isSuccessful) throw UserApiException(ErrorCode.UNAUTHORIZED_KAKAO)
        val kakaoRes = res.body()!!

        return SignOutResponse(
            id= kakaoRes.id
        )
    }
    */
}