package com.raphalsolarski.advent.y2023.d06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class D06Test {

    private val exampleInput = listOf(
        "Time:      7  15   30",
        "Distance:  9  40  200"
    )

    private val realInput = listOf(
        "Time:        49     78     79     80",
        "Distance:   298   1185   1066   1181"
    )

    @Test
    fun parseTest() {
        assertEquals(71530L to 940200L, D06.parseTimeToDistance(exampleInput))
    }

    @Test
    fun exampleS2() {
        assertEquals(71503, D06.computeResult(exampleInput))
    }

    @Test
    fun realS2() {
        assertEquals(35865985, D06.computeResult(realInput))
    }

}
