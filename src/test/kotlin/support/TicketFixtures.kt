package support

import com.cultureshock.madeleine.domain.ticket.Ticket
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketEntityResponse
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketPointAvgResponse
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketTopEntityResponse

fun createTicketPointAvgResponse(
    actorPointAvg: Double = 4.0,
    reviewPointAvg: Double = 1.0,
    seatPointAvg: Double = 4.0,
    stagePointAvg: Double = 3.0,
    storyPointAvg: Double = 2.0,
    trafficPointAvg: Double = 2.0,
    reviewCnt: Int = 1
): TicketPointAvgResponse {
    return TicketPointAvgResponse(
        actorPointAvg = actorPointAvg,
        reviewPointAvg = reviewPointAvg,
        seatPointAvg = seatPointAvg,
        stagePointAvg = stagePointAvg,
        storyPointAvg = storyPointAvg,
        trafficPointAvg = trafficPointAvg,
        reviewCnt = reviewCnt
    )
}
fun createTicketEntityResponse(
    ticketEntityResponse1: TicketEntityResponse = TicketEntityResponse(
        ticketId = 1,
        title = "제목1",
        companyName= "기획사1",
        regDate = 123,
        price = 10000,
        place = "장소1",
        seat = "14F",
        pointAvg = 3.5
    ),
    ticketEntityResponse2: TicketEntityResponse = TicketEntityResponse(
        ticketId = 2,
        title = "제목2",
        companyName= "기획사2",
        regDate = 123,
        price = 10000,
        place = "장소2",
        seat = "14F2",
        pointAvg = 4.0
    )
): List<TicketEntityResponse?>{
    return listOf<TicketEntityResponse?>(ticketEntityResponse1, ticketEntityResponse2)
}

fun createTicketTopEntityResponse(
    ticketTopEntityResponse1: TicketTopEntityResponse = TicketTopEntityResponse(
        ticketId = 1,
        nickName = "닉네임1",
        title= "티켓1",
        companyName = "회사1",
        regDate = 12313123,
        price = 12000,
        place = "장소1",
        seat = "13F",
        pointAvg = 3.2,
        like = 321
    ),
    ticketTopEntityResponse2: TicketTopEntityResponse = TicketTopEntityResponse(
        ticketId = 2,
        nickName = "닉네임2",
        title= "티켓2",
        companyName = "회사2",
        regDate = 12313123,
        price = 22000,
        place = "장소2",
        seat = "13F",
        pointAvg = 4.2,
        like = 123
    ),
): List<TicketTopEntityResponse> {
    return listOf<TicketTopEntityResponse>(ticketTopEntityResponse1, ticketTopEntityResponse2)
}
fun createTickets(
    ticket1: Ticket = Ticket(
        ticketId = 1L,
        userId = 1,
        performId = "PF131819",
        nickName =  "철수1",
        title = "캣츠",
        companyName = "혜화1극장",
        regDate = 1634724529,
        price = 13242,
        place = "예시장소",
        seat = "14f",
        cast = "아무개1",
        stagePoint = 3,
        storyPoint = 2,
        actorPoint = 4,
        trafficPoint = 2,
        seatPoint = 4,
        reviewPoint = 1,
        picture1 = "www.test01.com",
        picture2 = "www.test02.com",
        picture3 = "www.test03.com",
        picture4 = "www.test04.com",
        picture5 = "www.test05.com",
        review = "리뷰입니다1",
        like = 10,
    ),
    ticket2: Ticket = Ticket(
        ticketId = 2L,
        userId = 2,
        performId = "PF181298",
        nickName =  "철수2",
        title = "이터널스",
        companyName = "혜화2극장",
        regDate = 1634724529,
        price = 13242,
        place = "예시장소",
        seat = "14f",
        cast = "아무개2",
        stagePoint = 3,
        storyPoint = 2,
        actorPoint = 4,
        trafficPoint = 2,
        seatPoint = 4,
        reviewPoint = 1,
        picture1 = "www.test01.com",
        picture2 = "www.test02.com",
        picture3 = "www.test03.com",
        picture4 = "www.test04.com",
        picture5 = "www.test05.com",
        review = "리뷰입니다1",
        like = 30,

    ),
    ticket3: Ticket = Ticket(
        ticketId = 3L,
        userId = 2,
        performId = "PF181298",
        nickName =  "철수3",
        title = "오페라의유령",
        companyName = "혜화3극장",
        regDate = 1634724529,
        price = 13242,
        place = "예시장소",
        seat = "14f",
        cast = "최푸름",
        stagePoint = 3,
        storyPoint = 2,
        actorPoint = 4,
        trafficPoint = 2,
        seatPoint = 4,
        reviewPoint = 1,
        picture1 = "www.test01.com",
        picture2 = "www.test02.com",
        picture3 = "www.test03.com",
        picture4 = "www.test04.com",
        picture5 = "www.test05.com",
        review = "리뷰입니다1",
        like = 20
    ),
): List<Ticket> {
    return listOf<Ticket>(ticket1, ticket2, ticket3)
}


