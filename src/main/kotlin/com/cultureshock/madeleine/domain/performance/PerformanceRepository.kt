package com.cultureshock.madeleine.domain.performance

import com.cultureshock.madeleine.domain.performance.enum.HallLocation
import com.cultureshock.madeleine.domain.performance.enum.PerformKind
import com.cultureshock.madeleine.domain.performance.enum.PerformState
import com.cultureshock.madeleine.rest.dto.response.performance.PerformanceEntityResponse
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import javax.annotation.Resource

/**
 * Performance
 */
@Repository
interface PerformanceRepository : JpaRepository<Performance,Long>, CustomPerformanceRepository{
}
interface CustomPerformanceRepository{
    fun findAll(pageable: Pageable): Page<Performance>
}
/**
 * PerformanceDetail
 */
@Repository
interface PerformanceDetailRepository : JpaRepository<PerformanceDetail, Long>, CustomPerformanceDetailRepository{
}

interface CustomPerformanceDetailRepository{
    fun findAllByPerformKindAndPerformState(
        @Param("kind") performKind: Int,
        @Param("state") state: Int,
        @Param("location") hallAddress: Int,
        pageable: Pageable
    ): Page<PerformanceEntityResponse>
    fun findByPerformId(performId: String): PerformanceDetail?
}

/**
 * Location
 */
@Repository
interface LocationDetailRepository : JpaRepository<LocationDetail, Long>, CustomLocationDetailRepository{
}
interface CustomLocationDetailRepository : JpaRepository<LocationDetail, Long>{
    fun findByHallId(hallId: String) : LocationDetail?
}

/**
 * Reposotory Query DSL
 */

class PerformanceDetailRepositorySupport(
    @Resource(name = "jpaQueryFactory")
    val query: JPAQueryFactory
) : QuerydslRepositorySupport(PerformanceDetail::class.java), CustomPerformanceDetailRepository{

    private val perform : QPerformanceDetail = QPerformanceDetail.performanceDetail
    private val location : QLocationDetail = QLocationDetail.locationDetail

    override fun findByPerformId(
        performId: String
    ): PerformanceDetail? {
        return query.selectFrom(QPerformanceDetail.performanceDetail)
            .where(QPerformanceDetail.performanceDetail.performId.eq(performId))
            .fetchOne()
    }

    override fun findAllByPerformKindAndPerformState(
        @Param("kind") performKind: Int,
        @Param("state") state: Int,
        @Param("location") hallAddress: Int,
        pageable: Pageable): Page<PerformanceEntityResponse> {

        val result = query.select(
                Projections.constructor(
                    PerformanceEntityResponse::class.java,
                    perform.performId,
                    perform.performName,
                    perform.performStartDate,
                    perform.performEndDate,
                    perform.hallName,
                    perform.posterUrl,
                    perform.performState,
                    perform.performKind
                )
            )
            .from(perform)
            .join(location).on(perform.hallId.eq(location.hallId))
            .where(
                kindLike(performKind),
                stateEq(state),
                locationLike(hallAddress)
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        return PageImpl<PerformanceEntityResponse>(
            result,
            pageable,
            result.size.toLong()
        )
    }

    private fun kindLike(
        kind: Int
    ): BooleanExpression? {
        val performKind: String? = PerformKind.fromInt(kind).name
        if (kind == 0) return null
        else if(kind == 4) return !perform.performKind.like(PerformKind.fromInt(1).name)
            .and(!perform.performKind.like(PerformKind.fromInt(2).name)
                .and(!perform.performKind.like(PerformKind.fromInt(3).name)
                )
            )
        else return perform.performKind.like(performKind)
    }
    private fun stateEq(
        state: Int
    ): BooleanExpression? {
        val performState: String? = PerformState.fromInt(state).name
        return if (performState != null) perform.performState.eq(performState) else null
    }
    private fun locationLike(
        locationCode: Int
    ): BooleanExpression? {
        val hallLocation: String? = HallLocation.fromInt(locationCode).name

        when(locationCode){
            8 -> return location.hallAddres.like(HallLocation.fromInt(1).name)
                .and(location.hallAddres.like(HallLocation.fromInt(2).name))
                .and(location.hallAddres.like(HallLocation.fromInt(3).name))
                .and(location.hallAddres.like(HallLocation.fromInt(4).name))
                .and(location.hallAddres.like(HallLocation.fromInt(5).name))
                .and(location.hallAddres.like(HallLocation.fromInt(6).name))
                .and(location.hallAddres.like(HallLocation.fromInt(7).name))
                .and(location.hallAddres.like(HallLocation.fromInt(8).name))
            1,2,3,4,5,6,7 -> return location.hallAddres.like(hallLocation)
            else ->{
                return null
            }
        }
    }
}


class LocationDetailRepositorySupport(
    @Resource(name = "jpaQueryFactory")
    val query: JPAQueryFactory
) : QuerydslRepositorySupport(LocationDetail::class.java) {

    fun findByHallId(
        hallId: String
    ):  LocationDetail? {
        return query.selectFrom(QLocationDetail.locationDetail)
            .where(QLocationDetail.locationDetail.hallId.eq(hallId))
            .fetchOne()
    }
}