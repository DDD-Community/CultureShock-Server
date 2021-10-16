package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.config.web.dto.AuthenticatedUser
import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.service.user.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Api(tags = ["유저"])
@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService,
) : RestSupport() {

    @GetMapping
    @ApiOperation(value="유저 정보 조회", notes="JWT header 필요")
    fun getUserInfo(user: AuthenticatedUser): ResponseEntity<Any> {
        return response(userService.getUserInfo(user))
    }
}