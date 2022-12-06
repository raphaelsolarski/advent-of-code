package com.raphalsolarski.advent.y2022.d06

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class D06Test {

    private val realInput = ParseUtils.readLinesFromResource("/2022/06/input.txt")[0]

    @Test
    fun star1examplesTest() {
        assertEquals(5, D06.findStartMarker("bvwbjplbgvbhsrlpgdmjqwftvncz"))
    }

    @Test
    fun star1realTest() {
        assertEquals(1262, D06.findStartMarker(realInput))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(19, D06.findMessageMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    }

    @Test
    fun star2realTest() {
        assertEquals(3444, D06.findMessageMarker(realInput))
    }

}