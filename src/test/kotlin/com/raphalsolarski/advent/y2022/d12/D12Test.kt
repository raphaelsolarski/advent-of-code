package com.raphalsolarski.advent.y2022.d12

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2022.d09.D09
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D12Test {
    private val realInput = ParseUtils.readLinesFromResource("/2022/12/input.txt", withEmptyLines = false)

    private val exampleInput = listOf(
        "Sabqponm",
        "abcryxxl",
        "accszExk",
        "acctuvwj",
        "abdefghi",
    )

    @Test
    fun star1examplesTest() {
        assertEquals(31, D12.findClosestDistance(exampleInput))
    }

    @Test
    fun star1realTest() {
        assertEquals(6256, D12.findClosestDistance(realInput))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(1, D09.simulateAndCountVisitedByTail(exampleInput, tailSize = 9))
    }

    @Test
    fun star2realTest() {
        assertEquals(2665, D09.simulateAndCountVisitedByTail(realInput, tailSize = 9))
    }

}