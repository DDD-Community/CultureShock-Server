package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.config.web.dto.AuthenticatedUser
import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.service.performance.PerformanceService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(tags = ["공연"])
@RestController
@RequestMapping("/api/v1/performance")
class PerformanceController(
    private var performanceService: PerformanceService
): RestSupport() {

    @GetMapping("/list",produces = ["application/json"])
    @ApiOperation(value="공연 목록 조회", notes="공연 목록 조회 paging, order by 공연최신순, 공연중 only")
    fun getAll(@PageableDefault pageable: Pageable, @RequestParam("prfstate",required = false, defaultValue = "공연중") prfstate: String): ResponseEntity<Any> {
        return response(performanceService.findAllByPrfstate(pageable = pageable, prfstate = prfstate))
    }
    
    @GetMapping("/list/genrenm", produces = ["application/json"])
    @ApiOperation(value="장르별 공연 목록 조회", notes = "장르별 공연 목록 조회 paging, order by 공연최신순, 공연중 only")
    fun getAllByGenrenm(@PageableDefault pageable: Pageable, @RequestParam("prfstate",required = false, defaultValue = "공연중") prfstate: String, @RequestParam genrenm: String): ResponseEntity<Any> {
        return response(performanceService.findAllByGenrenmAndPrfstate(genrenm = genrenm, prfstate = prfstate, pageable))
    }

    @GetMapping("/{mt20id}", produces = ["application/json"])
    @ApiOperation(value="공연 상세 조회", notes = "공연 상세 조회 by 공연 ID")
    fun getPfById(user: AuthenticatedUser, @PathVariable mt20id: String): ResponseEntity<Any>{
        return response(performanceService.findByMt20id(mt20id))
    }
}