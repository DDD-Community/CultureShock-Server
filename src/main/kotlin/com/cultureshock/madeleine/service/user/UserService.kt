package com.cultureshock.madeleine.service.user

import com.cultureshock.madeleine.config.web.dto.AuthenticatedUser_v1
import com.cultureshock.madeleine.domain.user.QUserRepository
import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.exception.ApiNotFoundUserException
import com.cultureshock.madeleine.rest.dto.response.UserResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getUserInfoByEmail(email: String): UserResponse? {
        val user = userRepository.findByEmail(email)
        return user?.let { UserResponse.of(it) }
    }

    fun getUserInfo(id: Long): UserResponse {
        val user = userRepository.findById(id).orElseThrow(){ ApiNotFoundUserException() }
        return UserResponse.of(user)
    }
}