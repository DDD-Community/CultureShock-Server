package com.cultureshock.madeleine.service.performance

import com.cultureshock.madeleine.domain.performance.*
import com.cultureshock.madeleine.exception.ArguExistPerformanceException
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceDetailResponse
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceListResponse
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Transactional
@Component
class PerformanceService(
    private val performanceRepository: PerformanceRepository,
    private val performanceDetailRepository: PerformanceDetailRepository,
    private val locationDetailRepository: LocationDetailRepository
) {
    /**
     * @title 공연중(prfstate)인 공연 목록
     * @param Pageable
     * @return Page<Performance>
     */
    fun findAllByPrfstate(pageable: Pageable): PerformanceListResponse {
        var performance: MutableList<Performance>  = performanceRepository.findAllByPrfstateOrderByPrfpdfrom("공연중",pageable).content
        return PerformanceListResponse.of(performance);
    }

    /**
     * @title 공연 상세
     * @param mt20id :String 공연ID
     * @return PerformanceDetailResponse()
     * @exception ArguExistPerformanceException(400,"매칭되는 공연 ID가 없습니다")
     */
    fun findByMt20id(mt20id: String): PerformanceDetailResponse {
        val performance = performanceDetailRepository.findByMt20id(mt20id)?:throw ArguExistPerformanceException();
        return PerformanceDetailResponse.of(performance)
    }
}