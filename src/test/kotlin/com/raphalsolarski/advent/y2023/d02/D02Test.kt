package com.raphalsolarski.advent.y2023.d02

import com.raphalsolarski.advent.y2023.d02.D02.CubeSet
import com.raphalsolarski.advent.y2023.d02.D02.Game
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class D02Test {

    @Test
    fun parseTest() {
        assertEquals(
            Game(
                1, listOf(
                    CubeSet(4, 0, 3),
                    CubeSet(1, 2, 6),
                    CubeSet(0, 2, 0)
                )
            ),
            D02.parseLine("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        )
    }
}
