package com.cultureshock.madeleine.domain.ticket

import com.cultureshock.madeleine.domain.AbstractBaseAuditEntity
import com.cultureshock.madeleine.domain.user.Authority
import javax.persistence.*

@Entity
@Table(name="ticket")
class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val ticketId: Long? = null,

    @Column(nullable = false)
    val userId:Long,

    @Column
    val content: String = "",

    @Column
    val performId: String = "",

    @Column
    val nickName: String = "",

    @Column(nullable = false)
    val title: String, //공연종류

    @Column
    val companyName: String = "",

    @Column
    val regDate: Int = 0,

    @Column(name = "ticketPrice")
    val price: Int = 0,
    @Column(name = "ticketPlace")
    val place: String? = null,
    @Column(name = "ticketSeat")
    val seat: String? = null,
    @Column(name = "ticketCast")
    val cast: String? = null,

    val stagePoint: Double = 0.0,
    val storyPoint: Double = 0.0,
    val actorPoint: Double = 0.0,
    val trafficPoint: Double = 0.0,
    val seatPoint: Double = 0.0,
    val reviewPoint: Double = 0.0,

    val picture1: String? = null,
    val picture2: String? = null,
    val picture3: String? = null,
    val picture4: String? = null,
    val picture5: String? = null,

    @Column(name = "ticketReview")
    val review: String? = null,
    @Column(name = "ticketLike")
    val like: Int = 0,
    val pointAvg: Double = 0.0

): AbstractBaseAuditEntity()