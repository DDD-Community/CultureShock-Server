package com.cultureshock.madeleine.rest.dto.request

import com.cultureshock.madeleine.domain.ticket.Ticket
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class  TicketRegisRequest(
    val userid: Long = 0,
    val title: String = "",
    val content: String? = "",
    val regdate: Int? = 0,
    val price: Int? = 0,
    val companyname: String? = "",
    val photolist: List<String?>? = null,
    val seat: String? = "",
    val place: String? = "",
    val stagepoint: Double? = 0.0,
    val storypoint: Double? = 0.0,
    val actorpoint: Double? = 0.0,
    val trafficpoint: Double? = 0.0,
    val seatpoint: Double? = 0.0,
    val reviewpoint: Double? = 0.0
) {
    fun of(ticketRegisRequest: TicketRegisRequest): Ticket{
        return Ticket(
            userId = ticketRegisRequest.userid,
            title = ticketRegisRequest.title,
            companyName = ticketRegisRequest.companyname!!,
            price = ticketRegisRequest.price!!,
            seat = ticketRegisRequest.seat,
            place = ticketRegisRequest.place,
            regDate = ticketRegisRequest.regdate!!,
            content = ticketRegisRequest.content!!,
            picture1 = ticketRegisRequest.photolist?.get(0),
            picture2 = ticketRegisRequest.photolist?.get(1),
            picture3 = ticketRegisRequest.photolist?.get(2),
            picture4 = ticketRegisRequest.photolist?.get(3),
            picture5 = ticketRegisRequest.photolist?.get(4),
            actorPoint = ticketRegisRequest.actorpoint!!,
            seatPoint = ticketRegisRequest.seatpoint!!,
            reviewPoint = ticketRegisRequest.reviewpoint!!,
            storyPoint = ticketRegisRequest.storypoint!!,
            stagePoint = ticketRegisRequest.stagepoint!!,
            trafficPoint = ticketRegisRequest.trafficpoint!!,
            pointAvg = (Math.round(
                (ticketRegisRequest.actorpoint
                        +ticketRegisRequest.seatpoint
                        +ticketRegisRequest.reviewpoint
                        +ticketRegisRequest.storypoint
                        +ticketRegisRequest.stagepoint
                        +ticketRegisRequest.trafficpoint)/6.0)*100)/100.0
        )
    }
}






