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
    fun findAllByPrfstate(prfstate:String): List<Performance>?

    @Query("select a from Performance a where a.genrenm like %:genrenm% and a.prfstate like %:prfstate% ORDER BY a.prfpdfrom DESC")
    fun findAllByGenrenmAndPrfstate(@Param("genrenm") genrenm: String, @Param("prfstate") prfstate: String, pageable: Pageable): Page<Performance>
}

@Repository
interface PerformanceDetailRepository : JpaRepository<PerformanceDetail, Long>{
    fun findByMt20id(mt20id: String): PerformanceDetail?
}

@Repository
interface LocationDetailRepository : JpaRepository<LocationDetail, Long>{
    fun findByMt10id(mt10id: String) : LocationDetail?
}