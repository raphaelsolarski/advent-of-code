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
        assertEquals(listOf(7 to 9, 15 to 40, 30 to 200), D06.parseTimeToDistance(exampleInput))
    }

    @Test
    fun exampleS1() {
        assertEquals(288, D06.computeResult(exampleInput))
    }

    @Test
    fun realS1() {
        assertEquals(2269432, D06.computeResult(realInput))
    }

}
