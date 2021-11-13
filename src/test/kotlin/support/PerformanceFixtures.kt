package support

import com.cultureshock.madeleine.domain.performance.LocationDetail
import com.cultureshock.madeleine.domain.performance.Performance
import com.cultureshock.madeleine.domain.performance.PerformanceDetail
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceEntityResponse
import java.time.LocalDate
import java.util.*

fun createPerformanceEntityResponse(
    performanceEntityResponse1: PerformanceEntityResponse = PerformanceEntityResponse(
        performId = "MF123",
        performName = "공연1",
        performStartDate = Date(2016,6,23),
        performEndDate = Date(2016,8,28),
        hallName = "샘터파랑새극장",
        posterUrl = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF131819_160607_152247.jpg",
        performState = "공연중",
        performKind = "뮤지컬"
    ),
    performanceEntityResponse2: PerformanceEntityResponse = PerformanceEntityResponse(
        performId = "QF123",
        performName = "공연2",
        performStartDate = Date(2016,6,23),
        performEndDate = Date(2016,8,28),
        hallName = "샘터파랑새극장2",
        posterUrl = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF131819_160607_152247.jpg",
        performState = "공연예정",
        performKind = "연극"
    ),
): List<PerformanceEntityResponse>{
    return listOf(performanceEntityResponse1,performanceEntityResponse2)
}



fun createPerformances(
    performance1: Performance = Performance(
        id = 1L,
        performId = "PF131819",
        performName = "종이아빠",
        performStartDate = Date(2016,6,23),
        performEndDate = Date(2016,8,28),
        hallName = "샘터파랑새극장",
        posterUrl = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF131819_160607_152247.jpg",
        performState = "공연중",
        performKind = "뮤지컬"
    ),
    performance2: Performance = Performance(
        id = 2L,
        performId = "PF181298",
        performName = "아리랑 랩소디 [충주]",
        performStartDate = Date(2021,10,19),
        performEndDate = Date(2021,10,19),
        hallName = "충주시문화회관",
        posterUrl = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF181298_211018_150141.jpg",
        performState = "공연완료",
        performKind = "국악"
    ),
    performance3: Performance = Performance(
        id = 3L,
        performId = "PF181380",
        performName = "카발레리아 루스티카나 [서울]",
        performStartDate = Date(2021,11,5),
        performEndDate = Date(2021,11,27),
        hallName = "아트홀제이",
        posterUrl = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF181380_211019_130632.gif",
        performState = "공연예정",
        performKind = "오페라"
    ),
): List<Performance> {
    return listOf<Performance>(performance1, performance2, performance3)
}

fun createPerformanceDetails(
    performanceDetail1: PerformanceDetail = PerformanceDetail(
        pid = 1L,
        performId = "PF131819",
        hallId = "FC000221",
        performName = "종이아빠",
        performStartDate = Date(2016,6,19),
        performEndDate = Date(2016,11,27),
        hallName = "파랑새극장(구. 샘터파랑새극장) (1관)",
        performCast = null,
        performCrew = "이지은",
        performRuntime = "50분",
        performAge = "24개월 이상",
        enterName = "아트컴퍼니 행복자",
        price = "전석 20,000원",
        posterUrl = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF131819_160908_093947.jpg",
        performKind = "뮤지컬",
        performState = "공연중",
        performPhotoUrl1 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF131819_160819_1043401.jpg",
        performPhotoUrl2 = null,
        performPhotoUrl3 = null,
        performPhotoUrl4 = null,
        performTime = "월요일 ~ 화요일(11:00), 수요일 ~ 금요일(11:00,14:00), 토요일 ~ 일요일(12:00,14:00)"
    ),
    performanceDetail2: PerformanceDetail = PerformanceDetail(
        pid = 2L,
        performId = "PF181298",
        hallId = "FC000405",
        performName = "아리랑 랩소디 [충주]",
        performStartDate = Date(2021,10,19),
        performEndDate = Date(2021,10,19),
        hallName = "충주시문화회관 (충주시문화회관)",
        performCast = null,
        performCrew = null,
        performRuntime = "1시간 10분",
        performAge = "전체 관람가",
        enterName = null,
        price = "전석무료",
        posterUrl = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF181298_211018_150141.jpg",
        performKind = "국악",
        performState = "공연완료",
        performPhotoUrl1 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF181298_211018_0304110.jpg",
        performPhotoUrl2 = null,
        performPhotoUrl3 = null,
        performPhotoUrl4 = null,
        performTime = "화요일(17:00)"
    ),
    performanceDetail3: PerformanceDetail = PerformanceDetail(
        pid = 3L,
        performId = "PF181380",
        hallId = "FC001808",
        performName = "카발레리아 루스티카나 [서울]",
        performStartDate = Date(2021,11,5),
        performEndDate = Date(2021,11,27),
        hallName = "아트홀제이 (아트홀제이)",
        performCast = "김민정, 김대천, 신배윤, 김경연, 김시언",
        performCrew = "김민정, 최진혁, 김시언 등",
        performRuntime = "1시간 30분",
        performAge = "만 13세 이상",
        enterName = "(유)아트홀제이, (주)레벨투",
        price = "전석 50,000원",
        posterUrl = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF181380_211019_130632.gif",
        performKind = "오페라",
        performState = "공연예정",
        performPhotoUrl1 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF181380_211019_0106323.jpg",
        performPhotoUrl2 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF181380_211019_0106323.jpg",
        performPhotoUrl3 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF181380_211019_0106321.jpg",
        performPhotoUrl4 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF181380_211019_0106320.jpg",
        performTime = "금요일(20:00), 토요일(17:00)"
    )
):List<PerformanceDetail>{
    return listOf<PerformanceDetail>(performanceDetail1,performanceDetail2,performanceDetail3)
}

fun createLocationDetail(
    locationDetail1: LocationDetail = LocationDetail(
        lid = 1L,
        hallId = "FC000221",
        hallName = "파랑새극장(구. 샘터파랑새극장)",
        hallUrl = "http://www.parangsae.theater",
        hallAddres = "서울특별시 종로구 대학로 116 (동숭동)",
        la = 37.5815334,
        lo = 127.00241119999998
    ),
    locationDetail2: LocationDetail = LocationDetail(
        lid = 2L,
        hallId = "FC000405",
        hallName = "충주시문화회관",
        hallUrl = "https://www.chungju.go.kr/culture/",
        hallAddres = "충청북도 충주시 중앙로 128 (성내동)",
        la = 36.9707081,
        lo = 127.93725459999996
    ),
    locationDetail3: LocationDetail = LocationDetail(
        lid = 3L,
        hallId = "FC001808",
        hallName = "아트홀제이",
        hallUrl = "http://arthallj.com/",
        hallAddres = "서울특별시 송파구 위례성대로 18 금복빌딩 B1층 아트홀제이",
        la = 37.5164165,
        lo = 127.11430530000007
    ),
): List<LocationDetail>{
    return listOf<LocationDetail>(locationDetail1,locationDetail2,locationDetail3)
}