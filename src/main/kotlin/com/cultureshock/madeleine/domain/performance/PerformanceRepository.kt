package com.cultureshock.madeleine.domain.performance

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PerformanceRepository : JpaRepository<Performance,Long>{

    override fun findAll(pageable: Pageable): Page<Performance>
    fun findAllByPrfstateOrderByPrfpdfrom(prfstate:String, pageable: Pageable): Page<Performance>

    @Query("select a from Performance a where a.genrenm like %:keyword% and a.prfstate like %:공연중% ORDER BY a.prfpdfrom DESC")
    fun findAllByGenrenmAndPrfstate(@Param("keyword") keyword: String): List<Performance>
}

@Repository
interface PerformanceDetailRepository : JpaRepository<PerformanceDetail, Long>{
    fun findByMt20id(mt20id: String): PerformanceDetail?
}

@Repository
interface LocationDetailRepository : JpaRepository<LocationDetail, Long>{

}