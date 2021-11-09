package com.cultureshock.madeleine.service

import com.cultureshock.madeleine.domain.performance.*
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceDetailResponse
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceListResponse
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
    fun `공연 목록을 불러온다`(){


    }
}