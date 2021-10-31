package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.rest.dto.request.PerformanceListRequest
import com.cultureshock.madeleine.rest.dto.request.PerformanceSearchRequest
import com.cultureshock.madeleine.service.performance.PerformanceService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import okhttp3.Response
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(tags = ["공연"])
@RestController
@RequestMapping("/api/v1/performance")
class PerformanceController(
    private var performanceService: PerformanceService
): RestSupport() {

    @PostMapping("/list")
    @ApiOperation(value="공연 목록 조회", notes = "장르, 공연상태, 지역별 공연 목록 조회")
    fun getAllByGenrenmAndStateAndLocation(
        @RequestBody(required = false) performanceListRequest: PerformanceListRequest?
    ): ResponseEntity<Any> {
        val paging: Pageable = PageRequest.of(performanceListRequest?.page?: 0,performanceListRequest?.size?: 10)
        return response(performanceService.findAllJoinFetch(performanceListRequest?.kind?: 0,
            performanceListRequest?.state?: 0, performanceListRequest?.location?: 0, paging))
    }

    @PostMapping("/search")
    @ApiOperation(value="공연 목록 검색", notes = "타이틀 검색")
    fun getAllBykeyword(
        @RequestBody(required = false) performanceSearchRequest: PerformanceSearchRequest?
    ): ResponseEntity<Any>{
        val paging: Pageable = PageRequest.of(performanceSearchRequest?.page?: 0,performanceSearchRequest?.size?: 10)
        return response(performanceService.findByKeywordAll(performanceSearchRequest?.keyword?:"", paging))

    }

    @GetMapping("/{performId}", produces = ["application/json"])
    @ApiOperation(value="공연 상세 조회", notes = "공연 상세 조회 by 공연 ID")
    fun getPfById(
        @PathVariable performId: String): ResponseEntity<Any>{
        return response(performanceService.findByPerformId(performId))
    }


    /*
    @GetMapping("/list",produces = ["application/json"])
    @ApiOperation(value="공연 목록 조회", notes="공연 목록 조회 paging, order by 공연최신순, 공연중 only")
    fun getAll(
        @PageableDefault pageable: Pageable,
        @RequestParam("prfstate",required = false, defaultValue = "공연중") prfstate: String
    ): ResponseEntity<Any> {
        return response(performanceService.findAllByPrfstate(pageable = pageable, prfstate = prfstate))
    }
    */

    /*
    @GetMapping("/list/genrenm", produces = ["application/json"])
    @ApiOperation(value="장르별 공연 목록 조회", notes = "장르별 공연 목록 조회 paging, order by 공연최신순, 공연중 only")
    fun getAllByGenrenm(
        @PageableDefault pageable: Pageable,
        @RequestParam("prfstate", required = false, defaultValue = "공연중")
        prfstate: String,
        @RequestParam genrenm: String
    ): ResponseEntity<Any> {
        return response(performanceService.findAllByGenrenmAndPrfstate(genrenm = genrenm, prfstate = prfstate, pageable))
    }
    */
}