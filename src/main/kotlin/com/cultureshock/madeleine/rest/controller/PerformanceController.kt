package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.config.security.LoginUser
import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.service.performance.PerformanceService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
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
        @RequestBody(required = false) kind: Int,
        @RequestBody(required = false) state: Int,
        @RequestBody(required = false) location: Int,
        @PageableDefault pageable: Pageable
    ): ResponseEntity<Any> {
        return response(performanceService.findAllJoinFetch(kind, state, location, pageable))
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