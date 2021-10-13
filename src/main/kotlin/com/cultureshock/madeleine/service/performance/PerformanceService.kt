package com.cultureshock.madeleine.service.performance

import com.cultureshock.madeleine.domain.performance.Performance
import com.cultureshock.madeleine.domain.performance.PerformanceRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PerformanceService(
    private val performanceRepository: PerformanceRepository
) {
    @Transactional
    fun getList(): List<Performance> {
        return performanceRepository.findAll();
    }
}