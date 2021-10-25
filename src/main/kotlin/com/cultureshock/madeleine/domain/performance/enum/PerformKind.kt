package com.cultureshock.madeleine.domain.performance.enum

enum class PerformKind(val value: Int)  {
    전체(0),
    연극(1),
    뮤지컬(2),
    오페라(3),
    기타(4);

    companion object {
        fun fromInt(value: Int) = PerformKind.values().first { it.value == value }
    }
}