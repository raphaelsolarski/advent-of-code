package com.raphalsolarski.advent.y2023.d04

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2023.d04.D04.ScratchCard
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class D04Test {

    private val exampleInput = listOf(
        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
    )

    private val realInput = ParseUtils.readLinesFromResource("/2023/04/input.txt", withEmptyLines = false)

    @Test
    fun parseLineTest() {
        Assertions.assertEquals(
            ScratchCard(1, listOf(41, 48, 83, 86, 17), listOf(83, 86, 6, 31, 17, 9, 48, 53)),
            D04.parseLineToScratchCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
        )
    }

    @Test
    fun exampleS1() {
        Assertions.assertEquals(13, D04.computeTotalPoints(exampleInput))
    }

    @Test
    fun realS1() {
        Assertions.assertEquals(26914, D04.computeTotalPoints(realInput))
    }

    @Test
    fun exampleS2() {
        Assertions.assertEquals(30, D04.computeRemainingScratchCardsCount(exampleInput))
    }

    @Test
    fun realS2() {
        Assertions.assertEquals(13080971, D04.computeRemainingScratchCardsCount(realInput))
    }

}
