package com.cultureshock.madeleine.service.ticket

import com.cultureshock.madeleine.domain.ticket.CustomTicketRepository
import com.cultureshock.madeleine.domain.ticket.Ticket
import com.cultureshock.madeleine.domain.ticket.TicketRepository
import com.cultureshock.madeleine.exception.ArguExistPerformanceException
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceDetailResponse
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceListResponse
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketDetailResponse
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketEntityResponse
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketListResponse
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketPointAvgResponse
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class TicketService(
    private val ticketRepository: TicketRepository
) {
    /**
     * @title 공연별 티켓 목록
     * @param performId :Long 공연ID
     * @return PointAvg()
     * @exception ArguExistPerformanceException(400,"매칭되는 ID가 없습니다")
     */
    fun findByPerformId(
        performId:String
    ): TicketPointAvgResponse{
        val tickets = ticketRepository.findByPerformId(performId)
        val cnt = tickets.size;
        var pointAvg: TicketPointAvgResponse = TicketPointAvgResponse(0.0,0.0,0.0,0.0,0.0,0.0, 0)

        tickets.forEach {
            ticket ->
                pointAvg.stagePointAvg += ticket.stagePoint
                pointAvg.seatPointAvg += ticket.seatPoint
                pointAvg.actorPointAvg += ticket.actorPoint
                pointAvg.trafficPointAvg += ticket.trafficPoint
                pointAvg.storyPointAvg += ticket.storyPoint
                pointAvg.reviewPointAvg += ticket.reviewPoint
        }

        pointAvg.stagePointAvg /= cnt
        pointAvg.seatPointAvg /= cnt
        pointAvg.actorPointAvg /= cnt
        pointAvg.trafficPointAvg /= cnt
        pointAvg.storyPointAvg /= cnt
        pointAvg.reviewPointAvg /= cnt
        pointAvg.reviewCnt = cnt

        return pointAvg
    }


    /**
     * @title 티켓 상세
     * @param ticketId :Long 티켓ID
     * @return TicketDetailResponse()
     * @exception ArguExistPerformanceException(400,"매칭되는 ID가 없습니다")
     */
    fun findByTicketId(
        ticketId:Long
    ): TicketDetailResponse{
        val ticket = ticketRepository.findByTicketId(ticketId)?:throw ArguExistPerformanceException()
        return TicketDetailResponse.of(ticket)
    }

    /**
     * @title 마이 티켓 목록
     * @param userId :Long 유저ID
     * @return TicketListResponse
     */
    fun findAllByUserId(
        userId: Long,
        pageable: Pageable
    ): TicketListResponse {
        val result = ticketRepository.findAllByUserId(userId,pageable)
        val ticketList = result.content
        val totalCount: Long = result.totalElements
        val totalPages: Int = result.totalPages

        return TicketListResponse.of(ticketList, totalCount, totalPages)
    }
}