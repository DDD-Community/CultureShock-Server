package com.cultureshock.madeleine.rest.dto.response

import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.domain.user.enum.Sex
import com.cultureshock.madeleine.domain.user.enum.SocialType

data class UserResponse(
    val id: Long,
    val nickname: String,
    val email: String,
) {
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                id = user.id,
                nickname = user.nickname,
                email = user.email,
            )
        }
    }
}