package com.raphalsolarski.advent.y2022.d14

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class D14Test {
    private val realInput = ParseUtils.readLinesFromResource("/2022/14/input.txt", withEmptyLines = false)

    private val exampleInput = listOf(
        "498,4 -> 498,6 -> 496,6",
        "503,4 -> 502,4 -> 502,9 -> 494,9",
    )

    @Test
    fun star2examplesTest() {
        assertEquals(93, D14.simulateAndCountSandInRestBeforeFallingIntoAbyss(exampleInput))
    }

    @Test
    fun star2realTest() {
        assertEquals(745, D14.simulateAndCountSandInRestBeforeFallingIntoAbyss(realInput))
    }

}