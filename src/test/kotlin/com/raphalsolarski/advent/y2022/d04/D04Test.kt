package com.raphalsolarski.advent.y2022.d04

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class D04Test {

    private val exampleInput = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8",
    )

    private val realInput = ParseUtils.readLinesFromResource("/2022/04/input.txt")

    @Test
    fun star1examplesTest() {
        assertEquals(2, D04.countPairThatFullyOverlap(exampleInput))
    }

    @Test
    fun star1realTest() {
        assertEquals(441, D04.countPairThatFullyOverlap(realInput))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(4, D04.countPairThatOverlap(exampleInput))
    }

    @Test
    fun star2realTest() {
        assertEquals(861, D04.countPairThatOverlap(realInput))
    }

}