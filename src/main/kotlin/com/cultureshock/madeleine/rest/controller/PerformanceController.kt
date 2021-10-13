package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.domain.performance.Performance
import com.cultureshock.madeleine.service.performance.PerformanceService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/performance")
class PerformanceController(
    private var performanceService: PerformanceService
) {
    @GetMapping("/all",produces = ["application/json"])
    fun getAll(): List<Performance> {
        return performanceService.getList();
    }
}