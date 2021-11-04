package com.cultureshock.madeleine.rest.dto.response.performance

import com.cultureshock.madeleine.domain.performance.LocationDetail
import com.cultureshock.madeleine.domain.performance.Performance
import com.cultureshock.madeleine.domain.performance.PerformanceDetail
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketPointAvgResponse
import java.time.LocalDate
import java.util.*

data class PerformanceResponse (
    val id: Long,
    val performId: String,
    val performName: String,
    val performStartDate: Date,
    val performEndDate: Date,
    val hallName: String,
    val posterUrl: String,
    var performState: String,
    val performKind: String,
) {
    companion object {
        fun of(performance: Performance): PerformanceResponse {
            return PerformanceResponse(
                id = performance.id,
                performId = performance.performId,
                performName = performance.performName,
                performStartDate = performance.performStartDate,
                performEndDate = performance.performEndDate,
                hallName = performance.hallName,
                posterUrl = performance.posterUrl,
                performState = performance.performState,
                performKind = performance.performKind
            )
        }
    }
}
data class PerformanceDetailListResponse (
    val totalCount: Long,
    val totalPages: Int,
    val performanceList: MutableList<PerformanceDetail>
){
    companion object{
        fun of(performanceDetailList: MutableList<PerformanceDetail>, totalCount: Long, totalPages: Int ): PerformanceDetailListResponse{
            return PerformanceDetailListResponse(
                totalCount = totalCount,
                totalPages = totalPages,
                performanceList = performanceDetailList
            )
        }
    }
}


data class PerformanceListResponse (
    val totalCount: Long,
    val totalPages: Int,
    val performanceList: MutableList<PerformanceEntityResponse>
){
    companion object{
        fun of(performanceList: MutableList<PerformanceEntityResponse>, totalCount: Long, totalPages: Int ): PerformanceListResponse{
            return PerformanceListResponse(
                totalCount = totalCount,
                totalPages = totalPages,
                performanceList = performanceList
            )
        }
    }
}

data class PerformanceDetailResponse (
    val id: Long, //seq
    val performId: String,      //공연ID
    val performName: String,       //공연명
    var enterName: String?,   //제작사
    val performStartDate: Date,     //공연시작일
    val performEndDate: Date,       //공연종료일
    val hallName: String?,    //공연시설명(공연장명)
    val hallLocationX: Double?,
    val hallLocationY: Double?,
    val hallUrl: String?,
    val performCast: String?,    //공연출연진
    val performRuntime: String?, //공연런타임
    var price: String,//티켓가격
    val performKind: String?,     //장르
    var performState: String?,    //공연상태
    val posterUrl: String,      //포스터 이미지 경로
    val performPhotoUrl : List<String?>,
    val performTime: String?,   //공연시간

    val stagePointAvg: Double,
    val storyPointAvg: Double,
    val actorPointAvg: Double,
    val trafficPointAvg: Double,
    val seatPointAvg: Double,
    val reviewPointAvg: Double,
    val reviewCnt: Int
) {
    companion object {
        fun of(performanceDetail: PerformanceDetail, locationDetail: LocationDetail?, pointAvg: TicketPointAvgResponse): PerformanceDetailResponse {
            return PerformanceDetailResponse(
                id = performanceDetail.pid,
                performId = performanceDetail.performId,
                performKind = performanceDetail.performKind,
                performName = performanceDetail.performName,
                performStartDate = performanceDetail.performStartDate,
                performEndDate = performanceDetail.performEndDate,
                hallName = performanceDetail.hallName,
                performCast = performanceDetail.performCast,
                performRuntime = performanceDetail.performRuntime,
                enterName = performanceDetail.enterName,
                price = performanceDetail.price,
                posterUrl = performanceDetail.posterUrl,
                performState = performanceDetail.performState,
                performPhotoUrl = listOf(performanceDetail.performPhotoUrl1,performanceDetail.performPhotoUrl2,performanceDetail.performPhotoUrl3,performanceDetail.performPhotoUrl4),
                performTime = performanceDetail.performTime,
                hallUrl = locationDetail!!.hallUrl,
                hallLocationX = locationDetail!!.la,
                hallLocationY = locationDetail!!.lo,
                actorPointAvg = pointAvg.actorPointAvg,
                reviewPointAvg = pointAvg.reviewPointAvg,
                seatPointAvg = pointAvg.seatPointAvg,
                stagePointAvg = pointAvg.stagePointAvg,
                storyPointAvg = pointAvg.storyPointAvg,
                trafficPointAvg = pointAvg.trafficPointAvg,
                reviewCnt = pointAvg.reviewCnt
            )
        }
    }
}

data class PerformanceEntityResponse(
    val performId: String,
    val performName: String,
    val performStartDate: Date,
    val performEndDate: Date,
    val hallName: String?,
    val posterUrl: String,
    var performState: String?,
    val performKind: String?
)

data class LocationDetailResponse (
    val hallId: String,       //공연시설ID
    val hallName: String,      //공연 시설 명
    var hallUrl: String?,   //홈페이지
    var hallAddres: String?,       //주소
    var la: Double?,          //위도
    var lo: Double?           //경도
) {
    companion object {
        fun of(locationDetail: LocationDetail): LocationDetailResponse {
            return LocationDetailResponse(
                hallId = locationDetail.hallId,
                hallName = locationDetail.hallName,
                hallUrl = locationDetail.hallUrl,
                hallAddres = locationDetail.hallAddres,
                la = locationDetail.la,
                lo = locationDetail.lo
            )
        }
    }
}