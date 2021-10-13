package com.cultureshock.madeleine.domain.performance

import com.cultureshock.madeleine.domain.AbstractBaseAuditEntity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="performance")
class Performance (
        @Id
        @Column(name = "id", nullable = false)
        var id: Long? = null,

        @Column(name = "pfId", nullable= false)
        var mt20id: String,

        @Column(name = "title", nullable = false)
        var prfnm: String,

        @Column(name = "pfStart", nullable = false)
        var prfpdfrom: Date,

        @Column(name = "pfEnd", nullable = false)
        var prfpdto: Date,

        @Column(name = "pfPlace", nullable = false)
        var fcltynm: String,

        @Column(name = "poster")
        var poster: String,

        @Column(name = "pdYN", nullable = false)
        var prfstate: Char,

        @Column(name = "genre")
        var genrenm: String

        ) : AbstractBaseAuditEntity()