package com.raphalsolarski.advent.y2023.d01

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2019.d02.D2Impl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class D01Test {

    private val realInput = ParseUtils.readLinesFromResource("/2023/01/input.txt", withEmptyLines = false)

    private val exampleInputS1 = listOf(
        "1abc2",
        "pqr3stu8vwx",
        "a1b2c3d4e5f",
        "treb7uchet",
    )

    private val exampleInputS2 = listOf(
        "two1nine",
        "eightwothree",
        "abcone2threexyz",
        "xtwone3four",
        "4nineeightseven2",
        "zoneight234",
        "7pqrstsixteen",
    )

    @Test
    fun star1examplesTest() {
        val sum = exampleInputS1.sumOf { D01.findAndCombineNumbers(it) }
        assertEquals(142, sum)
    }

    @Test
    fun star1realTest() {
        val sum = realInput.sumOf { D01.findAndCombineNumbers(it) }
        assertEquals(53334, sum)
    }

    @Test
    fun star2examplesTest() {
        val sum = exampleInputS2.sumOf { D01.findAndCombineNumbersS2(it) }
        assertEquals(281, sum)
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource(
        "two1nine|29",
        "eightwothree|83",
        "abcone2threexyz|13",
        "xtwone3four|24",
        "4nineeightseven2|42",
        "zoneight234|14",
        "7pqrstsixteen|76",
        delimiter = '|'
    )
    fun `part2 line by line`(input: String, expected: Int) {
        val output = D01.findAndCombineNumbersS2(input)
        assertEquals(expected, output)
    }

    @Test
    fun star2RealTest() {
        val sum = realInput.sumOf { D01.findAndCombineNumbersS2(it) }
        assertEquals(52834, sum)
    }

}
