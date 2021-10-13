package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.config.web.dto.AuthenticatedUser
import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.service.user.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(tags = ["유저 정보 조회 및 수정"])
@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService,
) : RestSupport() {
    @PostMapping
    @ApiImplicitParams(
        ApiImplicitParam(
            name = "Authorization",
            value = "",
            required = true,
            allowEmptyValue = false,
            paramType = "header",
            dataTypeClass = String::class,
            example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdHkiOlt7ImF1dGhvcml0eSI6IkxFVkVMMCJ9XSwic3ViIjoia2FrYW8xNTE0NjYxOTk4IiwiYXVkIjoibW9iaWxlIiwiaWF0IjoxNjAzODY4OTg3LCJleHAiOjE2MDUzNDg5ODd9.wf8la-S_BP011E6ufCAC7eOp3nJghZ5RbuZ57GmN9vD3bkdxH2aCRSoff6FTHYZs6L9urRdXS64Z2R4kWppKhA"
        )
    )
    fun getUserInfo(user: AuthenticatedUser): ResponseEntity<Any> {
        userService.getUserInfo(user)
        return response("ok")
    }
}