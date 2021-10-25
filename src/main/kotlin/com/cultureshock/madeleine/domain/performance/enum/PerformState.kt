package com.cultureshock.madeleine.domain.performance.enum

enum class PerformState(val value: Int) {
    공연중(0),
    공연예정(1),
    공연종료(2);

    companion object {
        fun fromInt(value: Int) = PerformState.values().first { it.value == value }
    }
}
