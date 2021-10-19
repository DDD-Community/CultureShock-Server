package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.config.web.dto.AuthenticatedUser
import com.cultureshock.madeleine.domain.performance.Performance
import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.service.performance.PerformanceService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import retrofit2.adapter.rxjava.Result.response

@Api(tags = ["공연"])
@RestController
@RequestMapping("/api/v1/performance")
class PerformanceController(
    private var performanceService: PerformanceService
): RestSupport() {
    @GetMapping("/list",produces = ["application/json"])
    @ApiOperation(value="공연 정보 조회", notes="공연 정보 조회 paging, order by 공연최신순, 공연중 only")
    fun getAll(user: AuthenticatedUser, @RequestParam pageable: Pageable): ResponseEntity<Any> {
        return response(performanceService.findAllByPrfstate(pageable));
    }
}