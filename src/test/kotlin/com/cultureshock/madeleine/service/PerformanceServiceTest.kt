package com.cultureshock.madeleine.service

import com.cultureshock.madeleine.domain.performance.LocationDetailRepository
import com.cultureshock.madeleine.domain.performance.Performance
import com.cultureshock.madeleine.domain.performance.PerformanceDetailRepository
import com.cultureshock.madeleine.domain.performance.PerformanceRepository
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceDetailResponse
import com.cultureshock.madeleine.service.performance.PerformanceService
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

    @BeforeEach
    internal fun setUp() {
        performanceService = PerformanceService(
            performanceRepository = performanceRepository,
            performanceDetailRepository = performanceDetailRepository,
            locationDetailRepository = locationDetailRepository
        )
    }
/*
    @Test
    fun `현재 공연중인 공연목록 중 장르별로 불러온다`(){
        //given
        val performances: List<Performance> = createPerformances()
        val pageable: Pageable = PageRequest.of(0,2)
        val page: Page<Performance> = PageImpl(performances,pageable,3)

        //when
        every{ performanceRepository.findAllByGenrenmAndPrfstate("오페라","공연중",pageable) } answers { page }
        val response = performanceService.findAllByGenrenmAndPrfstate("오페라","공연중", pageable)

        //then
        assertAll(
            { Assertions.assertThat(response.totalCount).isEqualTo(3)},
            { Assertions.assertThat(response.totalPages).isEqualTo(2)},
            { Assertions.assertThat(response.performanceList[2].performName).isEqualTo("카발레리아 루스티카나 [서울]")},
            { Assertions.assertThat(response.performanceList[2].performId).isEqualTo("PF181380")}
        )
    }
 */
    @Test
    fun `공연 ID로 공연 상세를 불러온다`(){
        //when
        every{ performanceDetailRepository.findByPerformId(any())} answers { createPerformanceDetails()[2] }
        every{ locationDetailRepository.findByHallId(any())} answers { createLocationDetail()[2] }
        val response : PerformanceDetailResponse = performanceService.findByPerformId("FC001808")

        //then
        assertAll(
            { Assertions.assertThat(response.performKind).isEqualTo("오페라") },
            { Assertions.assertThat(response.performRuntime).isEqualTo("1시간 30분") },
            { Assertions.assertThat(response.locationDetailResponse?.hallAddres).isEqualTo("서울특별시 송파구 위례성대로 18 금복빌딩 B1층 아트홀제이") }
        )
    }
}