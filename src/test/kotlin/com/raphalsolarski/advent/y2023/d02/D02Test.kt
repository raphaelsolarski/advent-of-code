package com.raphalsolarski.advent.y2023.d02

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2023.d02.D02.CubeSet
import com.raphalsolarski.advent.y2023.d02.D02.Game
import com.raphalsolarski.advent.y2023.d02.D02.parseLine
import com.raphalsolarski.advent.y2023.d02.D02.testPossibility
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class D02Test {

    private val exampleS1Data = listOf(
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
    )

    private val realInput = ParseUtils.readLinesFromResource("/2023/02/input.txt", withEmptyLines = false)

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
            parseLine("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        )
    }

    @Test
    fun exampleS1() {
        val idSum = exampleS1Data.map(::parseLine)
            .filter(::testPossibility)
            .sumOf(Game::id)
        assertEquals(8, idSum)
    }

    @Test
    fun realS1() {
        val idSum = realInput.map(::parseLine)
            .filter(::testPossibility)
            .sumOf(Game::id)
        assertEquals(2449, idSum)
    }

}
