package com.cultureshock.madeleine.auth.security.service

import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.exception.ApiNotFoundUserException
import com.cultureshock.madeleine.auth.security.JwtUserFactory
import com.cultureshock.madeleine.domain.user.KakaoUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service(value = "userDetailsService")
class JwtUserDetailsService(
        private val kakaoUserRepository: KakaoUserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val user = kakaoUserRepository.findByUsernameAndActive(username) ?: throw ApiNotFoundUserException()

        return JwtUserFactory.create(user)
    }
}