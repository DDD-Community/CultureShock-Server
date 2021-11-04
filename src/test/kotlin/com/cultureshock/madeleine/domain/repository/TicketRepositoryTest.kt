package com.cultureshock.madeleine.domain.repository

import com.cultureshock.madeleine.domain.ticket.Ticket
import com.cultureshock.madeleine.domain.ticket.TicketRepository
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketEntityResponse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import support.RepositoryTest
import support.createTickets

@RepositoryTest
class TicketRepositoryTest @Autowired constructor(
    val ticketRepository: TicketRepository
) {

    @BeforeEach
    @Throws(Exception::class)
    internal fun setUp() {
        val tickets = createTickets()
        ticketRepository.saveAll(tickets)
    }

    @AfterEach
    internal fun setDown(){
        ticketRepository.deleteAll()
    }

    @Test
    fun `티켓id 매핑 티켓을 가져온다`() {
        val ticketEx: Ticket? = ticketRepository.findByTicketId(1L)

        assertAll(
            { Assertions.assertThat(ticketEx!!.review).isEqualTo("리뷰입니다1") },
            { Assertions.assertThat(ticketEx!!.nickName).isEqualTo("철수1") },
            { Assertions.assertThat(ticketEx!!.title).isEqualTo("캣츠") }
        )
    }

    @Test
    fun `userid 매핑 티켓 목록을 가져온다`() {
        val pageable: Pageable = PageRequest.of(0, 10)
        val ticketEx: MutableList<TicketEntityResponse?> = ticketRepository.findAllByUserId(2, pageable).content;

        assertAll(
            { Assertions.assertThat(ticketEx!!.size).isEqualTo(2) },
            { Assertions.assertThat(ticketEx[0]!!.place).isEqualTo("예시장소") },
            { Assertions.assertThat(ticketEx[0]!!.companyName).isEqualTo("혜화2극장") },
            { Assertions.assertThat(ticketEx[0]!!.title).isEqualTo("이터널스") },
            { Assertions.assertThat(ticketEx[1]!!.title).isEqualTo("오페라의유령") }
        )
    }

}