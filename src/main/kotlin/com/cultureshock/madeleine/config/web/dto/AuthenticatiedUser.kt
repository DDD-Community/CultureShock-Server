package com.cultureshock.madeleine.config.web.dto

data class AuthenticatedUser(
    val uid: Long,
    val username: String,
    val token: String
)