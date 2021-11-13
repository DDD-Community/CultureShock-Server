package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.rest.dto.request.TicketMyListRequest
import com.cultureshock.madeleine.service.performance.PerformanceService
import com.cultureshock.madeleine.service.ticket.TicketService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(tags = ["티켓"])
@RestController
@RequestMapping("/api/v1/ticket")
class TicketController(
    private var ticketService: TicketService
): RestSupport() {

    @PostMapping("/my/list", produces = ["application/json"])
    @ApiOperation(value="내 티켓 전체 리스트 조회", notes = "내 티켓 전체 리스트 조회")
    fun getMyTicketList(
        @RequestBody ticketMyListRequest: TicketMyListRequest?
    ): ResponseEntity<Any> {
        val paging: Pageable = PageRequest.of(ticketMyListRequest?.page?: 0,ticketMyListRequest?.size?: 10)
        return response(ticketService.findAllByUserId(ticketMyListRequest?.userid?: 0, paging))
    }

    @GetMapping("/top/list", produces = ["application/json"])
    @ApiOperation(value="좋아요 Top 10 목록 리스트", notes = "탑 10 리스트 조회")
    fun getTop10List(): ResponseEntity<Any>{
        return response(ticketService.findAllByLike());
    }

    @GetMapping("/{ticketId}", produces = ["application/json"])
    @ApiOperation(value="티켓 상세 조회", notes = "티켓 상세 조회 by 티켓 ID")
    fun getByTicketId(
        @PathVariable ticketId: Long
    ): ResponseEntity<Any> {
        return response(ticketService.findByTicketId(ticketId))
    }
}