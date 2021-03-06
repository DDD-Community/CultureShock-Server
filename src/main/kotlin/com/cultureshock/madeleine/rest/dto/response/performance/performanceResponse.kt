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
    val performId: String,      //??????ID
    val performName: String,       //?????????
    var enterName: String?,   //?????????
    val performStartDate: Date,     //???????????????
    val performEndDate: Date,       //???????????????
    val hallName: String?,    //???????????????(????????????)
    val hallLocationX: Double?,
    val hallLocationY: Double?,
    val hallUrl: String?,
    val performCast: String?,    //???????????????
    val performRuntime: String?, //???????????????
    var price: String,//????????????
    val performKind: String?,     //??????
    var performState: String?,    //????????????
    val posterUrl: String,      //????????? ????????? ??????
    val performPhotoUrl : List<String?>,
    val performTime: String?,   //????????????

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
    val hallId: String,       //????????????ID
    val hallName: String,      //?????? ?????? ???
    var hallUrl: String?,   //????????????
    var hallAddres: String?,       //??????
    var la: Double?,          //??????
    var lo: Double?           //??????
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