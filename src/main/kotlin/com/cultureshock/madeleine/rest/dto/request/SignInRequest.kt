package com.cultureshock.madeleine.rest.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class SignInRequest_v1(
    val code: String,
    val redirectUri: String,
    val agreementMarketingRecv: Boolean
)

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class SignInRequest(
    val nickname: String,
    val email: String
)