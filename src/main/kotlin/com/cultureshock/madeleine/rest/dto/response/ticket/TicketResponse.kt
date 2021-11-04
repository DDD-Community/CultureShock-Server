package com.cultureshock.madeleine.rest.dto.response.ticket

import com.cultureshock.madeleine.domain.ticket.Ticket
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceEntityResponse
import java.util.*


data class TicketDetailResponse (
    val ticketId: Long?,
    val userId:Long,
    val nickName: String,
    val title: String,

    val companyName: String?,

    val regDate: Int,

    val price: Int,
    val place: String?,
    val seat: String?,
    val cast: String?,

    val stagePoint: Int,
    val storyPoint: Int,
    val actorPoint: Int,
    val trafficPoint: Int,
    val seatPoint: Int,

    val performPhotoUrl: List<String?>,

    val review: String?,
    val like: Int,
) {
    companion object {
        fun of(ticket: Ticket): TicketDetailResponse {
            return TicketDetailResponse(
                ticketId = ticket.ticketId,
                userId = ticket.userId,
                nickName = ticket.nickName,
                title = ticket.title,

                companyName = ticket.companyName,

                regDate = ticket.regDate,

                price = ticket.price,
                place = ticket.place,
                seat = ticket.seat,
                cast = ticket.cast,

                stagePoint = ticket.stagePoint,
                storyPoint = ticket.storyPoint,
                actorPoint = ticket.actorPoint,
                trafficPoint = ticket.trafficPoint,
                seatPoint = ticket.seatPoint,

                performPhotoUrl = listOf(ticket.picture1,ticket.picture2,ticket.picture3,ticket.picture4,ticket.picture5),
                review = ticket.review,
                like = ticket.like,
            )
        }
    }
}

data class TicketListResponse (
    val totalCount: Long,
    val totalPages: Int,
    val ticketList: MutableList<TicketEntityResponse?>
){
    companion object{
        fun of(ticketList: MutableList<TicketEntityResponse?>, totalCount: Long, totalPages: Int ): TicketListResponse{
            return TicketListResponse(
                totalCount = totalCount,
                totalPages = totalPages,
                ticketList = ticketList
            )
        }
    }
}

data class TicketEntityResponse(
    val ticketId: Long,
    val title: String,
    val companyName: String?,
    val date: Int,
    val price: Int,
    val place: String?,
    val seat: String?,
    val pointAvg: Double
)
data class TicketPointAvgResponse(
    var actorPointAvg: Double,
    var reviewPointAvg: Double,
    var seatPointAvg: Double,
    var stagePointAvg: Double,
    var storyPointAvg: Double,
    var trafficPointAvg: Double,
    var reviewCnt: Int
)