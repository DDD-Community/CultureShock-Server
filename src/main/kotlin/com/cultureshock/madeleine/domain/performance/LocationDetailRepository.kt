package com.cultureshock.madeleine.domain.performance

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.annotation.Resource

interface LocationDetailRepository : JpaRepository<LocationDetail, Long>, CustomLocationDetailRepository{
}

interface CustomLocationDetailRepository{
    fun findByHallId(hallId: String) : LocationDetail?
}


@Repository
class LocationDetailRepositoryImpl(
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