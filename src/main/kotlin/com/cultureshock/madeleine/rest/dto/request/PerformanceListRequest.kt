package com.cultureshock.madeleine.rest.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class PerformanceListRequest(
    val kind: Int? = 0,
    val state: Int? = 0,
    val location: Int? = 0,
    val size: Int? = 10,
    val page: Int? = 0
)