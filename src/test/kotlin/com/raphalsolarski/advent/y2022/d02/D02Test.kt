package com.raphalsolarski.advent.y2022.d02

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class D02Test {

    private val exampleInput = listOf(
        "A Y",
        "B X",
        "C Z",
    )

    private val realInput = ParseUtils.readLinesFromResource("/2022/02/input.txt")

    @Test
    fun star1examplesTest() {
        assertEquals(15, D02.calculateScores(exampleInput))
    }

    @Test
    fun star1realTest() {
        assertEquals(13809, D02.calculateScores(realInput))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(12, D02.calculateScoresWay2(exampleInput))
    }

    @Test
    fun star2realTest() {
        assertEquals(12316, D02.calculateScoresWay2(realInput))
    }

}