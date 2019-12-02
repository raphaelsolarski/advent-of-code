package com.raphalsolarski.advent.y2019.d02

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.lang.RuntimeException

internal class D1ImplTest {

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource(
            "1,0,0,0,99 | 2,0,0,0,99",
            "2,3,0,3,99 | 2,3,0,6,99",
            "2,4,4,5,99,0 | 2,4,4,5,99,9801",
            "1,1,1,4,99,5,6,0,99 | 30,1,1,4,2,5,6,0,99",
            delimiter = '|'
    )
    fun `part1 test cases`(input: String, expected: String) {
        var output = D2Impl.compute(splitToList(input))
        assertEquals(splitToList(expected), output)
    }

    private fun splitToList(input: String) = input.split(",").map { it.toInt() }

    @Test
    fun `part1 result`() {
        val input = parsedInput().toMutableList()
        input[1] = 12
        input[2] = 2
        val output = D2Impl.compute(input)
        println(output)
    }

//    @ParameterizedTest(name = "{0} -> {1}")
//    @CsvSource(
//            "12,    2",
//            "14,    2",
//            "1969,  654",
//            "100756,  33583"
//    )
//    fun `part2 test cases`(input: Long, expected: Long) {
//    }

    @Test
    fun `part2 result`() {
        val expectedValue = 19690720
        val input = parsedInput().toMutableList()
        for (noun in 0..99) {
            for (verb in 0..99) {
                input[1] = noun
                input[2] = verb
                val output = D2Impl.compute(input)
                if (output[0] == expectedValue) {
                    throw RuntimeException("Result noun: $noun verb: $verb")
                }
            }
        }
    }

    private fun parsedInput(): List<Int> {
        return splitToList(ParseUtils.readLines("/2019/02/input.txt")[0])
    }
}