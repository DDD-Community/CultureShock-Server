package com.cultureshock.madeleine.domain.performance.enum

enum class HallLocation(val value: Int) {
    전체(0),
    서울(1),
    경기도(2),
    인천(3),
    부산(4),
    대전(5),
    대구(6),
    광주(7),
    기타(8);

    companion object {
        fun fromInt(value: Int) = HallLocation.values().first { it.value == value }
    }
}