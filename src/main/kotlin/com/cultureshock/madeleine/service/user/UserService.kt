package com.cultureshock.madeleine.service.user

import com.cultureshock.madeleine.config.web.dto.AuthenticatedUser
import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.exception.ApiNotFoundUserException
import com.cultureshock.madeleine.rest.dto.response.UserResponse
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getUserInfo(user: AuthenticatedUser): UserResponse {
        val user = userRepository.findById(user.uid).orElseThrow { ApiNotFoundUserException() }
        return UserResponse.of(user)
    }
}