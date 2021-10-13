package com.cultureshock.madeleine.domain.performance

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PerformanceRepository : JpaRepository<Performance,Long>{
}