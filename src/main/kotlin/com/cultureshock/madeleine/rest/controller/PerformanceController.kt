package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.domain.performance.Performance
import com.cultureshock.madeleine.service.performance.PerformanceService
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@Api(tags = ["공연"])
@RestController
@RequestMapping("/api/v1/performance")
class PerformanceController(
    private var performanceService: PerformanceService
) {
    @GetMapping("/all",produces = ["application/json"])
    fun getAll(): List<Performance> {
        return performanceService.getList();
    }
}