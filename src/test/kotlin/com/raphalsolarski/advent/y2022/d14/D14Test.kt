package com.raphalsolarski.advent.y2022.d14

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2022.d12.D12
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D14Test {
    private val realInput = ParseUtils.readLinesFromResource("/2022/14/input.txt", withEmptyLines = false)

    private val exampleInput = listOf(
        "498,4 -> 498,6 -> 496,6",
        "503,4 -> 502,4 -> 502,9 -> 494,9",
    )

    @Test
    fun star1examplesTest() {
        assertEquals(24, D14.simulateAndCountSandInRestBeforeFallingIntoAbyss(exampleInput))
    }

    @Test
    fun star1realTest() {
        assertEquals(745, D14.simulateAndCountSandInRestBeforeFallingIntoAbyss(realInput))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(29, D12.findClosestDistanceFromAnyStartingPoint(exampleInput))
    }

    @Test
    fun star2realTest() {
        assertEquals(2665, D12.findClosestDistanceFromAnyStartingPoint(realInput))
    }
}