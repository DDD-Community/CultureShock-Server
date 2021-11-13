package com.cultureshock.madeleine.service

import com.cultureshock.madeleine.domain.ticket.TicketRepository
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceDetailResponse
import com.cultureshock.madeleine.rest.dto.response.ticket.*
import com.cultureshock.madeleine.service.ticket.TicketService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.data.domain.PageRequest
import support.*

@UnitTest
internal class TicketServiceTest {
    @MockK
    private lateinit var ticketRepository: TicketRepository

    private lateinit var ticketService: TicketService

    @BeforeEach
    internal fun setUp() {
        ticketService = TicketService(
            ticketRepository = ticketRepository,
        )
    }

    @Test
    fun `공연 ID로 티켓 상세 호출`(){
        //when
        every{ ticketRepository.findByPerformId(any()) } answers { createTickets() }
        val response : TicketPointAvgResponse = ticketService.findByPerformId("ex");

        //then
        assertAll(
            { Assertions.assertThat(response.stagePointAvg).isEqualTo(3.0) },
            { Assertions.assertThat(response.trafficPointAvg).isEqualTo(2.0) },
            { Assertions.assertThat(response.actorPointAvg).isEqualTo(4.0) },
            { Assertions.assertThat(response.reviewPointAvg).isEqualTo(1.0) },
            { Assertions.assertThat(response.storyPointAvg).isEqualTo(2.0) },
            { Assertions.assertThat(response.reviewCnt).isEqualTo(3) },
        )
    }

    @Test
    fun `좋아요 탑 10 리스트 노출`() {
        //when
        every { ticketRepository.findAllByLike(any()).content } answers { createTicketTopEntityResponse() }
        val response: MutableList<TicketTopEntityResponse?> = ticketService.findAllByLike();

        //then
        assertAll(
            { Assertions.assertThat(response.size).isEqualTo(2) },
            { Assertions.assertThat(response[0]!!.like).isEqualTo(321)},
            { Assertions.assertThat(response[1]!!.like).isEqualTo(123)}
        )
    }
    
    @Test
    fun `내 티켓 리스트 조회`() {
        //when
        every { ticketRepository.findAllByUserId(any(),any()).content } answers { createTicketEntityResponse() }
        every { ticketRepository.findAllByUserId(any(),any()).totalPages } answers { 1 }
        every { ticketRepository.findAllByUserId(any(),any()).totalElements } answers { 2 }
        val response: TicketListResponse = ticketService.findAllByUserId(1,pageable = PageRequest.of(0, 10));

        //then
        assertAll(
            { Assertions.assertThat(response.totalPages).isEqualTo(1) },
            { Assertions.assertThat(response.totalCount).isEqualTo(2) },
            { Assertions.assertThat(response.ticketList[0]!!.title).isEqualTo("제목1") },
            { Assertions.assertThat(response.ticketList[1]!!.title).isEqualTo("제목2") },
        )
    }

    @Test
    fun `공연 상세`() {
        //when
        every { ticketRepository.findByTicketId(any()) } answers { createTickets()[0] }
        val response: TicketDetailResponse = ticketService.findByTicketId(1)

        //then
        assertAll(
            { Assertions.assertThat(response.ticketId).isEqualTo(1) },
            { Assertions.assertThat(response.title).isEqualTo("캣츠") },
            { Assertions.assertThat(response.companyName).isEqualTo("혜화1극장") },
        )
    }
}