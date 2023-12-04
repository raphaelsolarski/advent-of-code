package com.raphalsolarski.advent.y2023.d03

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class D03Test {

    private val exampleInput = listOf(
        "467..114..",
        "...*......",
        "..35..633.",
        "......#...",
        "617*......",
        ".....+.58.",
        "..592.....",
        "......755.",
        "...$.*....",
        ".664.598..",
    )

    private val realInput = ParseUtils.readLinesFromResource("/2023/03/input.txt", withEmptyLines = false)

    @Test
    fun exampleS1() {
        Assertions.assertEquals(4361, D03.sumPartNumbers(exampleInput))
    }

    @Test
    fun realS1() {
        Assertions.assertEquals(4361, D03.sumPartNumbers(realInput))
    }

}
