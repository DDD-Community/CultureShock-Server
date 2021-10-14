package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.config.web.dto.AuthenticatedUser
import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.service.user.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Api(tags = ["유저 정보 조회 및 수정"])
@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService,
) : RestSupport() {

    @PostMapping
    fun getUserInfo(user: AuthenticatedUser): ResponseEntity<Any> {
        userService.getUserInfo(user)
        return response("ok")
    }
}