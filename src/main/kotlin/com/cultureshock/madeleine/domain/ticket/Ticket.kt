package com.cultureshock.madeleine.domain.ticket

import com.cultureshock.madeleine.domain.AbstractBaseAuditEntity
import com.cultureshock.madeleine.domain.user.Authority
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="ticket")
class Ticket(
    @Id
    @Column(nullable = false)
    val ticketId: Long? = null,

    @Column(nullable = false)
    val userId:Long,

    @Column(nullable = false)
    val nickName: String,

    @Column(nullable = false)
    val title: String, //공연종류

    val companyName: String? = null,

    val date: Int,

    val price: Int = 0,
    val place: String? = null,
    val seat: String? = null,
    val cast: String? = null,

    val stagePoint: Int = 0,
    val storyPoint: Int = 0,
    val actorPoint: Int = 0,
    val trafficPoint: Int = 0,
    val seatPoint: Int = 0,

    val picture1: String? = null,
    val picture2: String? = null,
    val picture3: String? = null,
    val picture4: String? = null,
    val picture5: String? = null,

    val review: String? = null,
    val like: Int = 0,
    val pointAvg: Double = 0.0

): AbstractBaseAuditEntity()