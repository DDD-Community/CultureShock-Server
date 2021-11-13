package com.cultureshock.madeleine.service

import com.cultureshock.madeleine.domain.performance.*
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceDetailResponse
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceListResponse
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketListResponse
import com.cultureshock.madeleine.service.performance.PerformanceService
import com.cultureshock.madeleine.service.ticket.TicketService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import support.*


@UnitTest
internal class PerformanceServiceTest {
    @MockK
    private lateinit var performanceRepository: PerformanceRepository

    @MockK
    private lateinit var performanceDetailRepository: PerformanceDetailRepository

    @MockK
    private lateinit var locationDetailRepository: LocationDetailRepository

    private lateinit var performanceService: PerformanceService

    @MockK
    private lateinit var ticketService: TicketService

    @BeforeEach
    internal fun setUp() {
        performanceService = PerformanceService(
            performanceRepository = performanceRepository,
            performanceDetailRepository = performanceDetailRepository,
            locationDetailRepository = locationDetailRepository,
            ticketService = ticketService
        )
    }

    @Test
    fun `공연 ID로 공연 상세를 불러온다`(){
        //when
        every{ performanceDetailRepository.findByPerformId(any())} answers { createPerformanceDetails()[2] }
        every{ locationDetailRepository.findByHallId(any())} answers { createLocationDetail()[2] }
        every{ ticketService.findByPerformId(any()) } answers { createTicketPointAvgResponse() }
        val response : PerformanceDetailResponse = performanceService.findByPerformId("FC001808")

        //then
        assertAll(
            { Assertions.assertThat(response.performKind).isEqualTo("오페라") },
            { Assertions.assertThat(response.performRuntime).isEqualTo("1시간 30분") },
        )
    }

    @Test
    fun `공연 목록을 불러온다`() {
        //when
        every { performanceDetailRepository.findAllJoinAll(any(), any(), any(),any()).content } answers { createPerformanceEntityResponse() }
        every { performanceDetailRepository.findAllJoinAll(any(), any(), any(),any()).totalPages } answers { 1 }
        every { performanceDetailRepository.findAllJoinAll(any(), any(), any(),any()).totalElements } answers { 2 }
        val response: PerformanceListResponse = performanceService.findAllJoinFetch(0,0,0,pageable = PageRequest.of(0, 10));

        //then
        assertAll(
            { Assertions.assertThat(response.totalPages).isEqualTo(1) },
            { Assertions.assertThat(response.totalCount).isEqualTo(2) },
            { Assertions.assertThat(response.performanceList[0]!!.performName).isEqualTo("공연1") },
            { Assertions.assertThat(response.performanceList[0]!!.performName).isEqualTo("공연2") },
        )
    }

    @Test
    fun `공연 목록을 키워드 별로 불러온다`() {
        //when
        every { performanceDetailRepository.findByKeywordAll(any(),any()).content } answers { listOf(createPerformanceEntityResponse()[0]) }
        every { performanceDetailRepository.findByKeywordAll(any(),any()).content } answers { listOf(createPerformanceEntityResponse()[1]) }
        every { performanceDetailRepository.findByKeywordAll( any(),any()).totalPages } answers { 1 }
        every { performanceDetailRepository.findByKeywordAll(any(),any()).totalElements } answers { 2 }
        val response_1: PerformanceListResponse = performanceService.findByKeywordAll("예시1",pageable = PageRequest.of(0, 10));
        val response_2: PerformanceListResponse = performanceService.findByKeywordAll("예시2",pageable = PageRequest.of(0, 10));

        //then
        assertAll(
            { Assertions.assertThat(response_1.totalPages).isEqualTo(1) },
            { Assertions.assertThat(response_1.totalCount).isEqualTo(2) },

        )
    }
}