package com.raphalsolarski.advent.y2018.d01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException
import java.util.*

internal class CalculatorTest {

    @Test
    fun part1TestCase() {
        val result = Calculator.addAll(listOf(1, 1, 1))
        assertEquals(3, result)
    }

    @Test
    fun part1TestCase2() {
        val numbers = listOf(1, 1, -2)
        val result = Calculator.addAll(numbers)
        assertEquals(0, result)
    }

    @Test
    fun part1TestCase3() {
        val numbers = listOf(-1, -2, -3)
        val result = Calculator.addAll(numbers)
        assertEquals(-6, result)
    }

    @Test
    fun part1Origin() {
        val result = Calculator.addAll(parseInput())
        println("result: $result")
    }

    // part 2

    @Test
    fun part2TestCase1() {
        val numbers = listOf(1, -2, 3, 1, 1, -2)
        val result = Calculator.findFrequency(numbers)
        assertEquals(2, result)
    }

    @Test
    fun part2Origin() {
        val numbers = parseInput()
        val result = Calculator.findFrequency(numbers)
        println("result: $result")
    }

    private fun parseInput(): List<Int> {
        val lines = javaClass.getResource("/2018/01/input.txt")?.readText()?.split("\n")
        if (lines != null) {
            return lines
                    .filter { it.isNotEmpty() }
                    .map { it.toInt() }
        } else {
            throw IllegalStateException("File can't be read")
        }
    }

}