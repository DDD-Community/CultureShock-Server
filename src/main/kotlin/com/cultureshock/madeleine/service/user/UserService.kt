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

    fun getUserInfoByEmail(email: String): UserResponse? {
        val user = userRepository.findByEmail(email)
        return user?.let { UserResponse.of(it) }
    }

    /**
     * @deprecated
     */
    fun getUserInfo(user: AuthenticatedUser): UserResponse {
        val user = userRepository.findById(user.id).orElseThrow { ApiNotFoundUserException() }
        return UserResponse.of(user)
    }
}