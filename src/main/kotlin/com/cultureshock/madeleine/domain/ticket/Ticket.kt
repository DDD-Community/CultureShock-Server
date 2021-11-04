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

    @Column(nullable = false)
    val performId: String,

    @Column(nullable = false)
    val nickName: String,

    @Column(nullable = false)
    val title: String, //공연종류

    val companyName: String? = null,

    val regDate: Int,

    @Column(name = "ticketPrice")
    val price: Int = 0,
    @Column(name = "ticketPlace")
    val place: String? = null,
    @Column(name = "ticketSeat")
    val seat: String? = null,
    @Column(name = "ticketCast")
    val cast: String? = null,

    val stagePoint: Int = 0,
    val storyPoint: Int = 0,
    val actorPoint: Int = 0,
    val trafficPoint: Int = 0,
    val seatPoint: Int = 0,
    val reviewPoint: Int = 0,

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