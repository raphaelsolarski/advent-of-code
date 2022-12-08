package com.raphalsolarski.advent.y2022.d08

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2022.d06.D06
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D08Test {

    private val realInput = ParseUtils.readLinesFromResource("/2022/08/input.txt")

    private val exampleInput = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390",
    )

    @Test
    fun star1examplesTest() {
        assertEquals(21, D08.countVisibleTrees(exampleInput))
    }

    @Test
    fun star1realTest() {
        assertEquals(1782, D08.countVisibleTrees(realInput))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(8, D08.findHighestScenicScore(exampleInput))
    }

    @Test
    fun star2realTest() {
//        assertEquals(3444, D06.findMessageMarker(realInput))
    }

}