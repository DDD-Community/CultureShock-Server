package com.cultureshock.madeleine.service.performance

import com.cultureshock.madeleine.domain.performance.*
import com.cultureshock.madeleine.domain.performance.enum.HallLocation
import com.cultureshock.madeleine.domain.performance.enum.PerformKind
import com.cultureshock.madeleine.domain.performance.enum.PerformState
import com.cultureshock.madeleine.exception.ArguExistPerformanceException
import com.cultureshock.madeleine.rest.dto.response.performance.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestBody


@Transactional
@Component
class PerformanceService(
    private val performanceRepository: PerformanceRepository,
    private val performanceDetailRepository: PerformanceDetailRepository,
    private val locationDetailRepository: LocationDetailRepository
) {
    /**
     * @title 공연상태 & 지역 & 공연 종류 로 매핑된 공연 목록
     * @param Pageable, kind, state, location
     * @return PerformanceListResponse() -> totalCount, totalPage, list
     */
    fun findAllByGenrenmAndStateAndLocation(
        kind: Int,
        state: Int,
        location: Int,
        pageable: Pageable
    ): PerformanceDetailListResponse{

        val performanceDetailList: MutableList<PerformanceEntityResponse> =
            performanceDetailRepository.findAllByGenrenmAndPrfstateAndLocation(kind, state, location, pageable).content

        val totalCount: Long = performanceDetailRepository.findAllByGenrenmAndPrfstateAndLocation(kind,state,location,pageable).totalElements
        val totalPages: Int = performanceDetailRepository.findAllByGenrenmAndPrfstateAndLocation(kind,state,location,pageable).totalPages

        return PerformanceDetailListResponse.of(performanceDetailList, totalCount, totalPages)
    }



    /**
     * @title 공연 상세
     * @param performId :String 공연ID
     * @return PerformanceDetailResponse()
     * @exception ArguExistPerformanceException(400,"매칭되는 공연 ID가 없습니다")
     */
    fun findByPerformId(
        performId: String
    ): PerformanceDetailResponse {
        val performance = performanceDetailRepository.findByPerformId(performId)?:throw ArguExistPerformanceException()
        val location = locationDetailRepository.findByHallId(performance.hallId)
        return PerformanceDetailResponse.of(performance, location)
    }

    /**
     * @title 공연상태 & 키워드로 매핑된 공연 목록
     * @param Pageable
     * @return PerformanceListResponse() -> totalCount, totalPage, list
     *

    fun findAllByGenrenmAndPrfstate(
        genrenm: String,
        prfstate: String,
        pageable: Pageable
    ): PerformanceListResponse{
        val performance: MutableList<Performance> =
            performanceRepository.findAllByGenrenmAndPrfstate(genrenm,prfstate,pageable).content
        val totalCount: Long = performanceRepository.findAllByGenrenmAndPrfstate(genrenm,prfstate,pageable).totalElements
        val totalPages: Int = performanceRepository.findAllByGenrenmAndPrfstate(genrenm,prfstate,pageable).totalPages
        return PerformanceListResponse.of(performance, totalCount, totalPages)
    }
    */
}