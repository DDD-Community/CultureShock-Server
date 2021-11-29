package com.cultureshock.madeleine.domain.ticket


import com.cultureshock.madeleine.rest.dto.request.TicketRegisRequest
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketEntityResponse
import com.cultureshock.madeleine.rest.dto.response.ticket.TicketTopEntityResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.util.*
import javax.annotation.Resource

interface TicketRepository : JpaRepository<Ticket, Long>, CustomTicketRepository{
}

interface CustomTicketRepository{
    fun findByTicketId(ticketId: Long): Ticket?

    fun findByPerformId(
        perforId: String
    ): List<Ticket>

    fun findAllByUserId(
        userId: Long,
        pageable: Pageable
    ): Page<TicketEntityResponse?>

    fun findAllByLike(
        pageable: Pageable
    ): Page<TicketTopEntityResponse?>
}

/**
 * Reposotory Query DSL
 */
@Repository
class TicketRepositoryImpl(
    @Resource(name = "jpaQueryFactory")
    val query: JPAQueryFactory
) : QuerydslRepositorySupport(Ticket::class.java), CustomTicketRepository{

    private val ticket : QTicket = QTicket.ticket

    override fun findAllByLike(
        pageable: Pageable
    ): Page<TicketTopEntityResponse?> {
        val result = query.select(
            Projections.constructor(
                TicketTopEntityResponse::class.java,
                ticket.ticketId,
                ticket.nickName,
                ticket.title,
                ticket.companyName,
                ticket.regDate,
                ticket.price,
                ticket.place,
                ticket.seat,
                ticket.pointAvg,
                ticket.like
            )
        ).from(ticket)
            .orderBy(ticket.like.desc()).fetch()

        return PageImpl<TicketTopEntityResponse>(
            result,
            pageable,
            result.size.toLong()
        )
    }
    override fun findByTicketId(
        ticketId: Long
    ): Ticket? {
        return query.selectFrom(ticket)
            .where(ticket.ticketId.eq(ticketId)).fetchOne()
    }

    override fun findByPerformId(
        performId: String
    ): List<Ticket>{
        return query.selectFrom(ticket)
            .where(ticket.performId.eq(performId)).fetch()
    }

    override fun findAllByUserId(
        userId: Long,
        pageable: Pageable
    ): Page<TicketEntityResponse?> {
        val result = query.select(
            Projections.constructor(
                TicketEntityResponse::class.java,
                    ticket.ticketId,
                    ticket.title,
                    ticket.companyName,
                    ticket.regDate,
                    ticket.price,
                    ticket.place,
                    ticket.seat,
                    ticket.pointAvg
            )
        ).from(ticket)
            .where(ticket.userId.eq(userId)).fetch()

        return PageImpl<TicketEntityResponse>(
            result,
            pageable,
            result.size.toLong()
        )
    }
}
