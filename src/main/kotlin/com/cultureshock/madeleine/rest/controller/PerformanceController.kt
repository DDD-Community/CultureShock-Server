package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.config.web.dto.AuthenticatedUser
import com.cultureshock.madeleine.domain.performance.Performance
import com.cultureshock.madeleine.service.performance.PerformanceService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@Api(tags = ["공연"])
@RestController
@RequestMapping("/api/v1/performance")
class PerformanceController(
    private var performanceService: PerformanceService
) {
    @GetMapping("/list",produces = ["application/json"])
    @ApiOperation(value="공연 정보 조회", notes="공연 정보 조회")
    fun getAll(user: AuthenticatedUser): List<Performance> {
        return performanceService.getList();
    }
}