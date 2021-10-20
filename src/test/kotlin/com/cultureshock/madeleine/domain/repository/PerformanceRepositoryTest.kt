package com.cultureshock.madeleine.domain.repository

import com.cultureshock.madeleine.domain.performance.*
import com.cultureshock.madeleine.exception.ArguExistPerformanceException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
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
        performanceDetailRepository.saveAll(performanceDetails)
        locationDetailRepository.saveAll(locationDetail)
    }

    @Test
    fun `조건없이 공연 목록을 전부 가져온다`() {
        val pageable: Pageable = PageRequest.of(0, 2)
        val pfList: MutableList<Performance> = performanceRepository.findAll(pageable).content

        assertAll(
            { assertThat(pfList.size).isEqualTo(2) },
            { assertThat(pfList[0].prfnm).isEqualTo("종이아빠") },
            { assertThat(pfList[0].genrenm).isEqualTo("뮤지컬") },
            { assertThat(pfList[1].prfnm).isEqualTo("아리랑 랩소디 [충주]") },
            { assertThat(pfList[1].prfstate).isEqualTo("공연완료") },
        )
    }

    @Test
    fun `현재 공연중 공연 목록을 최신 개봉순으로 가져온다`() {
        val pageable: Pageable = PageRequest.of(0, 10)
        val pfList: MutableList<Performance> =
            performanceRepository.findAllByPrfstateOrderByPrfpdfrom("공연중", pageable).content

        assertAll(
            { assertThat(pfList.size).isEqualTo(1) },
            { assertThat(pfList[0].prfnm).isEqualTo("종이아빠") },
            { assertThat(pfList[0].poster).isEqualTo("http://www.kopis.or.kr/upload/pfmPoster/PF_PF131819_160607_152247.jpg") },
        )
    }

    @Test
    fun `현재 공연 목록을 장르로 매핑 후 가져온다`() {
        val pageable: Pageable = PageRequest.of(0, 10)
        val pfList: MutableList<Performance> =
            performanceRepository.findAllByGenrenmAndPrfstate(genrenm = "오페라", prfstate = "공연예정", pageable).content

        assertAll(
            { assertThat(pfList.size).isEqualTo(1) },
            { assertThat(pfList[0].prfnm).isEqualTo("카발레리아 루스티카나 [서울]") },
            { assertThat(pfList[0].prfpdfrom).isEqualTo(LocalDate.of(2021, 11, 5)) },
        )
    }

    @Test
    fun `공연 목록 중 공연완료 목록으로 가져온다`() {
        val pageable: Pageable = PageRequest.of(0, 10)
        val pfList: MutableList<Performance> =
            performanceRepository.findAllByGenrenmAndPrfstate(genrenm = "국악", prfstate = "공연완료", pageable).content

        assertAll(
            { assertThat(pfList.size).isEqualTo(1) },
            { assertThat(pfList[0].prfnm).isEqualTo("아리랑 랩소디 [충주]") },
            { assertThat(pfList[0].prfstate).isEqualTo("공연완료") },
        )
    }

    @Test
    fun `공연ID로 공연 상세 및 공연 장소 상세 를 가져온다`() {
        val pfDetail: PerformanceDetail =
            performanceDetailRepository.findByMt20id("PF181298") ?: throw ArguExistPerformanceException()

        val pfLocation = locationDetailRepository.findByMt10id(pfDetail.mt10id)?: throw ArguExistPerformanceException()

        assertAll(
            //Performance Detail test
            { assertThat(pfDetail).isNotNull },
            { assertThat(pfDetail.prfnm).isEqualTo("아리랑 랩소디 [충주]") },
            { assertThat(pfDetail.prfstate).isEqualTo("공연완료") },
            //Location test
            { assertThat(pfLocation.seatscale).isEqualTo(933) },
            { assertThat(pfLocation.telno).isEqualTo("043-850-3914") },
            { assertThat(pfLocation.la).isEqualTo(36.9707081) }
        )
    }

    @Test
    fun `공연ID로 공연 상세 호출 시 매핑값이 없다면 예외를 불러온다`() {
        assertThrows<ArguExistPerformanceException> {
            val pfDetail: PerformanceDetail =
                performanceDetailRepository.findByMt20id("PF000000") ?: throw ArguExistPerformanceException()
        }
        assertThrows<ArguExistPerformanceException> {
            val pfLocation: LocationDetail =
                locationDetailRepository.findByMt10id("FP000000") ?: throw ArguExistPerformanceException()
        }
    }
}
