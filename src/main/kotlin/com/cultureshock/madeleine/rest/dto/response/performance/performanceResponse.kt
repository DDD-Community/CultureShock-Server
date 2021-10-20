package com.cultureshock.madeleine.rest.dto.response.performance

import com.cultureshock.madeleine.domain.performance.LocationDetail
import com.cultureshock.madeleine.domain.performance.Performance
import com.cultureshock.madeleine.domain.performance.PerformanceDetail
import java.time.LocalDate

data class PerformanceResponse (
    val id: Long,
    val mt20id: String,
    val prfnm: String,
    val prfpdfrom: LocalDate,
    val prfpdto: LocalDate,
    val fcltynm: String,
    val poster: String,
    var prfstate: String,
    val genrenm: String,
) {
    companion object {
        fun of(performance: Performance): PerformanceResponse {
            return PerformanceResponse(
                id = performance.id,
                mt20id = performance.mt20id,
                prfnm = performance.prfnm,
                prfpdfrom = performance.prfpdfrom,
                prfpdto = performance.prfpdto,
                fcltynm = performance.fcltynm,
                poster = performance.poster,
                prfstate = performance.prfstate,
                genrenm = performance.genrenm
            )
        }
    }
}

data class PerformanceListResponse (
    val totalCount: Long,
    val totalPages: Int,
    val performanceList: MutableList<Performance>
){
    companion object{
        fun of(performanceList: MutableList<Performance>, totalCount: Long, totalPages: Int ): PerformanceListResponse{
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
    val mt20id: String,      //공연ID
    val mt10id: String,      //공연시설ID
    val prfnm: String,       //공연명
    val prfpdfrom: LocalDate,     //공연시작일
    val prfpdto: LocalDate,       //공연종료일
    val fcltynm: String?,    //공연시설명(공연장명)
    val prfcast: String?,    //공연출연진
    val prfcrew: String?,    //공연제작진
    val prfruntime: String?, //공연런타임
    var prfage: String,//공연 관람 연령
    var entrpsnm: String?,   //제작사
    var pcseguidance: String,//티켓가격
    val poster: String,      //포스터 이미지 경로
    val sty: String?,         //줄거리
    val genrenm: String?,     //장르
    var prfstate: String?,    //공연상태
    var openrun: Char,        //오픈런
    val styurl_1: String?, // 소개이미지목록1
    val styurl_2: String?, // 소개이미지목록2
    val styurl_3: String?, // 소개이미지목록3
    val styurl_4: String?, // 소개이미지목록4
    var dtguidance: String?   //공연시간
) {
    companion object {
        fun of(performanceDetail: PerformanceDetail): PerformanceDetailResponse {
            return PerformanceDetailResponse(
                id = performanceDetail.id,
                mt20id = performanceDetail.mt20id,
                mt10id = performanceDetail.mt10id,
                prfnm = performanceDetail.prfnm,
                prfpdfrom = performanceDetail.prfpdfrom,
                prfpdto = performanceDetail.prfpdto,
                fcltynm = performanceDetail.fcltynm,
                prfcast = performanceDetail.prfcast,
                prfcrew = performanceDetail.prfcrew,
                prfruntime = performanceDetail.prfruntime,
                prfage = performanceDetail.prfage,
                entrpsnm = performanceDetail.entrpsnm,
                pcseguidance = performanceDetail.pcseguidance,
                poster = performanceDetail.poster,
                sty = performanceDetail.sty,
                genrenm = performanceDetail.genrenm,
                prfstate = performanceDetail.prfstate,
                openrun = performanceDetail.openrun,
                styurl_1 = performanceDetail.styurl_1,
                styurl_2 = performanceDetail.styurl_2,
                styurl_3 = performanceDetail.styurl_3,
                styurl_4 = performanceDetail.styurl_4,
                dtguidance = performanceDetail.dtguidance   //공연시간
            )
        }
    }
}

data class LocationDetailResponse (
    val id: Long,
    val mt10id: String,       //공연시설ID
    val fcltynm: String,      //공연 시설 명
    var mt13cnt: Int?,        //공연장 수
    var fcltychartr: String?, //시설 특성(ex. 기타(공공))
    var opende: String?,      //개관 연도
    var seatscale: Int?,      //객석 수
    var telno: String?,       //전화번호
    var relateurl: String?,   //홈페이지
    var adres: String?,       //주소
    var la: Double?,          //위도
    var lo: Double?           //경도
) {
    companion object {
        fun of(locationDetail: LocationDetail): LocationDetailResponse {
            return LocationDetailResponse(
                id = locationDetail.lid,
                mt10id = locationDetail.mt10id,
                fcltynm = locationDetail.fcltynm,
                mt13cnt = locationDetail.mt13cnt,
                fcltychartr = locationDetail.fcltychartr,
                opende = locationDetail.opende,
                seatscale = locationDetail.seatscale,
                telno = locationDetail.telno,
                relateurl = locationDetail.relateurl,
                adres = locationDetail.adres,
                la = locationDetail.la,
                lo = locationDetail.lo
            )
        }
    }
}