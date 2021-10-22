package com.cultureshock.madeleine.rest.dto.response

import com.cultureshock.madeleine.domain.user.enum.SocialType

class SignInResponse_v1(
    val nickname: String,
    val email: String,
    val socialType: SocialType,
    val token: String
)
class SignInResponse(
    val user_id: Long
)
