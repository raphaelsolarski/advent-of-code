package com.raphalsolarski.advent.y2019.d01

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class FuelCalculatorTest {

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource(
            "12,    2",
            "14,    2",
            "1969,  654",
            "100756,  33583"
    )
    fun `part1 test cases`(input: Long, expectedResult: Long) {
        assertEquals(expectedResult, FuelCalculator.compute(input))
    }

    @Test
    fun part1Result() {
        val input = parseInput()
        println(sumAll(input.map { FuelCalculator.compute(it) }))
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource(
            "14,    2",
            "1969,  966",
            "100756,  50346"
    )
    fun `part2 test cases`(input: Long, expectedResult: Long) {
        assertEquals(expectedResult, FuelCalculator.compute2(input))
    }

    @Test
    fun part2Result() {
        var input = parseInput()
        println(sumAll(input.map { FuelCalculator.compute2(it) }))
    }

    private fun sumAll(numbers: List<Long>): Long {
        return numbers.sum()
    }

    private fun parseInput(): List<Long> {
        return ParseUtils.readLinesFromResource("/2019/01/input.txt")
                .map { it.toLong() }
    }

}