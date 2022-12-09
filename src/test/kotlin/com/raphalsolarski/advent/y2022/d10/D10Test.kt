package com.raphalsolarski.advent.y2022.d10

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2022.d09.D09
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D10Test {

    private val realInput = ParseUtils.readLinesFromResource("/2022/10/input.txt")

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

    @Test
    fun star1examplesTest() {
        assertEquals(13, D10.theMethod(exampleInput))
    }

    @Test
    fun star1realTest() {
        assertEquals(6256, D10.theMethod(realInput))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(1, D10.theMethod2(exampleInput))
    }

    @Test
    fun star2realTest() {
        assertEquals(2665, D10.theMethod2(realInput))
    }

}