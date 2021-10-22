package com.cultureshock.madeleine.config.web.dto

data class AuthenticatedUser_v1(
    val uid: Long,
    val username: String,
    val token: String
)

data class AuthenticatedUser(
    val id: Long,
    val nickname: String,
    val email: String
)