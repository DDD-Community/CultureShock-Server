package support

import com.cultureshock.madeleine.domain.performance.LocationDetail
import com.cultureshock.madeleine.domain.performance.Performance
import com.cultureshock.madeleine.domain.performance.PerformanceDetail
import java.time.LocalDate

fun createPerformances(
    performance1: Performance = Performance(
        id = 1L,
        mt20id = "PF131819",
        prfnm = "종이아빠",
        prfpdfrom = LocalDate.of(2016,6,23),
        prfpdto = LocalDate.of(2016,8,28),
        fcltynm = "샘터파랑새극장",
        poster = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF131819_160607_152247.jpg",
        prfstate = "공연중",
        genrenm = "뮤지컬"
    ),
    performance2: Performance = Performance(
        id = 2L,
        mt20id = "PF181298",
        prfnm = "아리랑 랩소디 [충주]",
        prfpdfrom = LocalDate.of(2021,10,19),
        prfpdto = LocalDate.of(2021,10,19),
        fcltynm = "충주시문화회관",
        poster = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF181298_211018_150141.jpg",
        prfstate = "공연완료",
        genrenm = "국악"
    ),
    performance3: Performance = Performance(
        id = 3L,
        mt20id = "PF181380",
        prfnm = "카발레리아 루스티카나 [서울]",
        prfpdfrom = LocalDate.of(2021,11,5),
        prfpdto = LocalDate.of(2021,11,27),
        fcltynm = "아트홀제이",
        poster = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF181380_211019_130632.gif",
        prfstate = "공연예정",
        genrenm = "오페라"
    ),
): List<Performance> {
    return listOf<Performance>(performance1, performance2, performance3)
}

fun createPerformanceDetails(
    performanceDetail1: PerformanceDetail = PerformanceDetail(
        id = 1L,
        mt20id = "PF131819",
        mt10id = "FC000221",
        prfnm = "종이아빠",
        prfpdfrom = LocalDate.of(2016,6,19),
        prfpdto = LocalDate.of(2016,11,27),
        fcltynm = "파랑새극장(구. 샘터파랑새극장) (1관)",
        prfcast = null,
        prfcrew = "이지은",
        prfruntime = "50분",
        prfage = "24개월 이상",
        entrpsnm = "아트컴퍼니 행복자",
        pcseguidance = "전석 20,000원",
        poster = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF131819_160908_093947.jpg",
        sty = "목,금 2시/ 토,공휴일 11시, 1시/ 일 12시/ 월,화,수 쉼 8/19 : 공연없음 8/15 : 11시, 1시 9/3~9/25 : 토,일 12시, 2시 / 월~금 쉼 9/26~11/27 : 수,목,금 2시 / 토,일,공휴일 12시, 2시 / 월,화 쉼 (10/3 : 12시, 2시) 9월 4일, 10일, 11일, 17일, 18일 오후 2시 : 공연없음 10/3 : 12시, 2시",
        genrenm = "뮤지컬",
        prfstate = "공연중",
        openrun = 'N',
        styurl_1 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF131819_160819_1043401.jpg",
        styurl_2 = null,
        styurl_3 = null,
        styurl_4 = null,
        dtguidance = "월요일 ~ 화요일(11:00), 수요일 ~ 금요일(11:00,14:00), 토요일 ~ 일요일(12:00,14:00)"
    ),
    performanceDetail2: PerformanceDetail = PerformanceDetail(
        id = 2L,
        mt20id = "PF181298",
        mt10id = "FC000405",
        prfnm = "아리랑 랩소디 [충주]",
        prfpdfrom = LocalDate.of(2021,10,19),
        prfpdto = LocalDate.of(2021,10,19),
        fcltynm = "충주시문화회관 (충주시문화회관)",
        prfcast = null,
        prfcrew = null,
        prfruntime = "1시간 10분",
        prfage = "전체 관람가",
        entrpsnm = null,
        pcseguidance = "전석무료",
        poster = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF181298_211018_150141.jpg",
        sty = null,
        genrenm = "국악",
        prfstate = "공연완료",
        openrun = 'N',
        styurl_1 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF181298_211018_0304110.jpg",
        styurl_2 = null,
        styurl_3 = null,
        styurl_4 = null,
        dtguidance = "화요일(17:00)",
    ),
    performanceDetail3: PerformanceDetail = PerformanceDetail(
        id = 3L,
        mt20id = "PF181380",
        mt10id = "FC001808",
        prfnm = "카발레리아 루스티카나 [서울]",
        prfpdfrom = LocalDate.of(2021,11,5),
        prfpdto = LocalDate.of(2021,11,27),
        fcltynm = "아트홀제이 (아트홀제이)",
        prfcast = "김민정, 김대천, 신배윤, 김경연, 김시언",
        prfcrew = "김민정, 최진혁, 김시언 등",
        prfruntime = "1시간 30분",
        prfage = "만 13세 이상",
        entrpsnm = "(유)아트홀제이, (주)레벨투",
        pcseguidance = "전석 50,000원",
        poster = "http://www.kopis.or.kr/upload/pfmPoster/PF_PF181380_211019_130632.gif",
        sty = null,
        genrenm = "오페라",
        prfstate = "공연예정",
        openrun = 'N',
        styurl_1 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF181380_211019_0106323.jpg",
        styurl_2 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF181380_211019_0106323.jpg",
        styurl_3 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF181380_211019_0106321.jpg",
        styurl_4 = "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF181380_211019_0106320.jpg",
        dtguidance = "금요일(20:00), 토요일(17:00)"
    )
):List<PerformanceDetail>{
    return listOf<PerformanceDetail>(performanceDetail1,performanceDetail2,performanceDetail3)
}

fun createLocationDetail(
    locationDetail1: LocationDetail = LocationDetail(
        lid = 1L,
        mt10id = "FC000221",
        fcltynm = "파랑새극장(구. 샘터파랑새극장)",
        mt13cnt = 1,
        fcltychartr = "민간(대학로)",
        opende = "1984",
        seatscale = 400,
        telno = "02-744-0015",
        relateurl = "http://www.parangsae.theater",
        adres = "서울특별시 종로구 대학로 116 (동숭동)",
        la = 37.5815334,
        lo = 127.00241119999998
    ),
    locationDetail2: LocationDetail = LocationDetail(
        lid = 2L,
        mt10id = "FC000405",
        fcltynm = "충주시문화회관",
        mt13cnt = 1,
        fcltychartr = "공공(문예회관)",
        opende = "1985",
        seatscale = 933,
        telno = "043-850-3914",
        relateurl = "https://www.chungju.go.kr/culture/",
        adres = "충청북도 충주시 중앙로 128 (성내동)",
        la = 36.9707081,
        lo = 127.93725459999996
    ),
    locationDetail3: LocationDetail = LocationDetail(
        lid = 3L,
        mt10id = "FC001808",
        fcltynm = "아트홀제이",
        mt13cnt = 1,
        fcltychartr = "민간(대학로 외)",
        opende = "2016",
        seatscale = 30,
        telno = "010-4947-7748",
        relateurl = "http://arthallj.com/",
        adres = "서울특별시 송파구 위례성대로 18 금복빌딩 B1층 아트홀제이",
        la = 37.5164165,
        lo = 127.11430530000007
    ),
): List<LocationDetail>{
    return listOf<LocationDetail>(locationDetail1,locationDetail2,locationDetail3)
}