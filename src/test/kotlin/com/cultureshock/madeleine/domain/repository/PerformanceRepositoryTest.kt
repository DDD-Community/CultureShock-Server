package com.cultureshock.madeleine.domain.repository

import com.cultureshock.madeleine.domain.performance.*
import com.cultureshock.madeleine.exception.ArguExistPerformanceException
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceEntityResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import support.RepositoryTest
import support.createLocationDetail
import support.createPerformanceDetails
import support.createPerformances
import java.time.LocalDate

@RepositoryTest
class PerformanceRepositoryTest @Autowired constructor(
    val performanceRepository: PerformanceRepository,
    val performanceDetailRepository: PerformanceDetailRepository,
    val locationDetailRepository: LocationDetailRepository
) {
    @BeforeEach
    @Throws(Exception::class)
    internal fun setUp() {
        val performances = createPerformances()
        val performanceDetails = createPerformanceDetails()
        val locationDetail = createLocationDetail()

        performanceRepository.saveAll(performances)
        locationDetailRepository.saveAll(locationDetail)
        performanceDetailRepository.saveAll(performanceDetails)
    }

    @Test
    fun `조건없이 공연 목록을 전부 가져온다`() {
        val pageable: Pageable = PageRequest.of(0, 2)
        val pfList: MutableList<Performance> = performanceRepository.findAll(pageable).content

        assertAll(
            { assertThat(pfList.size).isEqualTo(2) },
            { assertThat(pfList[0].performName).isEqualTo("종이아빠") },
            { assertThat(pfList[0].performKind).isEqualTo("뮤지컬") },
            { assertThat(pfList[1].performName).isEqualTo("아리랑 랩소디 [충주]") },
            { assertThat(pfList[1].performState).isEqualTo("공연완료") },
        )
    }

    @Test
    fun `현재 공연중 공연 목록을 조건없이 최신 개봉순으로 가져온다`() {
        val pageable: Pageable = PageRequest.of(0, 10)
        val pfList: MutableList<PerformanceEntityResponse> =
            performanceDetailRepository.findAllByGenrenmAndPrfstateAndLocation(0,0,0, pageable).content

        assertAll(
            { assertThat(pfList.size).isEqualTo(3) },
            { assertThat(pfList[0].performName).isEqualTo("종이아빠") },
            { assertThat(pfList[0].posterUrl).isEqualTo("http://www.kopis.or.kr/upload/pfmPoster/PF_PF131819_160908_093947.jpg") },
            { assertThat(pfList[1].performName).isEqualTo("아리랑 랩소디 [충주]") },
            { assertThat(pfList[2].performName).isEqualTo("카발레리아 루스티카나 [서울]") },
        )
    }

    @Test
    fun `공연ID로 공연 상세 및 공연 장소 상세 를 가져온다`() {
        val pfDetail: PerformanceDetail =
            performanceDetailRepository.findByPerformId("PF181298") ?: throw ArguExistPerformanceException()

        val pfLocation = locationDetailRepository.findByHallId(pfDetail.hallId)?: throw ArguExistPerformanceException()

        assertAll(
            //Performance Detail test
            { assertThat(pfDetail).isNotNull },
            { assertThat(pfDetail.performName).isEqualTo("아리랑 랩소디 [충주]") },
            { assertThat(pfDetail.performState).isEqualTo("공연완료") },
            //Location test
            { assertThat(pfLocation.la).isEqualTo(36.9707081) }
        )
    }

    @Test
    fun `공연ID로 공연 상세 호출 시 매핑값이 없다면 예외를 불러온다`() {
        assertThrows<ArguExistPerformanceException> {
            val pfDetail: PerformanceDetail =
                performanceDetailRepository.findByPerformId("PF000000") ?: throw ArguExistPerformanceException()
        }
        assertThrows<ArguExistPerformanceException> {
            val pfLocation: LocationDetail =
                locationDetailRepository.findByHallId("FP000000") ?: throw ArguExistPerformanceException()
        }
    }
}
