package com.raphalsolarski.advent.y2022.d09

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class D09Test {

    private val realInput = ParseUtils.readLinesFromResource("/2022/09/input.txt")

    private val exampleInput = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2",
    )

    private val example2Input = listOf(
        "R 5",
        "U 8",
        "L 8",
        "D 3",
        "R 17",
        "D 10",
        "L 25",
        "U 20",
    )

    @Test
    fun star1examplesTest() {
        assertEquals(13, D09.simulateAndCountVisitedByTail(exampleInput, tailSize = 1))
    }

    @Test
    fun star1realTest() {
        assertEquals(6256, D09.simulateAndCountVisitedByTail(realInput, tailSize = 1))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(1, D09.simulateAndCountVisitedByTail(exampleInput, tailSize = 9))
    }

    @Test
    fun star2example2Test() {
        assertEquals(36, D09.simulateAndCountVisitedByTail(example2Input, tailSize = 9))
    }

    @Test
    fun star2realTest() {
        assertEquals(2665, D09.simulateAndCountVisitedByTail(realInput, tailSize = 9))
    }

}