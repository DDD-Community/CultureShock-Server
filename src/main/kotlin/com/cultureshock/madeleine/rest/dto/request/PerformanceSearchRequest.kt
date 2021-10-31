package com.cultureshock.madeleine.rest.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class PerformanceSearchRequest(
    val keyword: String? = "",
    val size: Int? = 10,
    val page: Int? = 0
)