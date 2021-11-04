package com.cultureshock.madeleine.service

import com.cultureshock.madeleine.domain.ticket.TicketRepository
import com.cultureshock.madeleine.service.ticket.TicketService
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import support.UnitTest

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


}