package com.cultureshock.madeleine.domain.ticket

import com.cultureshock.madeleine.domain.AbstractBaseAuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="ticket")
class Ticket(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = true)
    var content: String? = null
): AbstractBaseAuditEntity()