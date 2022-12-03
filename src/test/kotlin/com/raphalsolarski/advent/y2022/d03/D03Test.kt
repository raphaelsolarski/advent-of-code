package com.raphalsolarski.advent.y2022.d03

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class D03Test {

    private val exampleInput = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw",
    )

    private val realInput = ParseUtils.readLinesFromResource("/2022/03/input.txt")

    @Test
    fun computeScoreTest() {
        assertEquals(27, D03.computeItemScore('A'))
        assertEquals(52, D03.computeItemScore('Z'))
        assertEquals(1, D03.computeItemScore('a'))
        assertEquals(26, D03.computeItemScore('z'))
    }

    @Test
    fun star1examplesTest() {
        assertEquals(157, D03.computeDuplicatedItemsScores(exampleInput))
    }

    @Test
    fun star1realTest() {
        assertEquals(8349, D03.computeDuplicatedItemsScores(realInput))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(70, D03.computeGroupBadgesPriorities(exampleInput))
    }

    @Test
    fun star2realTest() {
        assertEquals(2681, D03.computeGroupBadgesPriorities(realInput))
    }

}