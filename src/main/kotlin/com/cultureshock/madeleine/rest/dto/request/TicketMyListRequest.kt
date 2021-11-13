package com.cultureshock.madeleine.rest.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class TicketMyListRequest(
    val userid:Long = 0,
    val size: Int? = 10,
    val page: Int? = 0
)

