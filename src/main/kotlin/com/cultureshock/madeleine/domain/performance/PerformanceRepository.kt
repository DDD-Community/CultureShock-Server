package com.cultureshock.madeleine.domain.performance

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 * Performance
 */
@Repository
interface PerformanceRepository : JpaRepository<Performance,Long>, CustomPerformanceRepository{
}
interface CustomPerformanceRepository{
    fun findAll(pageable: Pageable): Page<Performance>
}