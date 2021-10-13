package com.cultureshock.madeleine.rest.dto.response

import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.domain.user.enum.Sex
import com.cultureshock.madeleine.domain.user.enum.SocialType

data class UserResponse(
    val uid: Long,
    val name: String,
    val nickname: String,
    val phone: String,
    val email: String,
    val sex: Sex,
    val socialType: SocialType
) {
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                uid = user.id,
                name = user.username,
                nickname = user.nickname,
                phone = user.phone ?: "",
                email = user.email,
                sex = user.sex ?: Sex.UNKNOWN,
                socialType = user.socialType
            )
        }
    }
}