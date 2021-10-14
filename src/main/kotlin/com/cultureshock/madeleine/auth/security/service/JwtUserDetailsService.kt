package com.cultureshock.madeleine.auth.security.service

import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.exception.ApiNotFoundUserException
import com.cultureshock.madeleine.auth.security.JwtUserFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service(value = "userDetailsService")
class JwtUserDetailsService(
        private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val user = userRepository.findByUsernameAndActive(username) ?: throw ApiNotFoundUserException()

        return JwtUserFactory.create(user)
    }
}