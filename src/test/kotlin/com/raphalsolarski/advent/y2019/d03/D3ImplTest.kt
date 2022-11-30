package com.raphalsolarski.advent.y2019.d03

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class D3ImplTest {

    @Test
    fun `part1 - cross points`() {
        val wire1 = "R8,U5,L5,D3".split(",")
        val wire2 = "U7,R6,D4,L4".split(",")
        val wires = listOf(wire1, wire2)

        val crossPoints = D3Impl.findCrossPoints(wires)
                .map { it.point }
        assertTrue(crossPoints.contains(Pair(3, 3)))
        assertTrue(crossPoints.contains(Pair(6, 5)))
        val minDistance = D3Impl.minDistance(wires)
        assertEquals(6, minDistance)
    }

    @ParameterizedTest(name = "{0}, {1} -> {2}")
    @CsvSource(
            "R75,D30,R83,U83,L12,D49,R71,U7,L72 | U62,R66,U55,R34,D71,R55,D58,R83 | 159",
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51 | U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 | 135",
            delimiter = '|'
    )
    fun `part1 test cases`(in1: String, in2: String, expectedResult: Int) {
        val wire1 = in1.split(",")
        val wire2 = in2.split(",")
        val wires = listOf(wire1, wire2)
        val minDistance = D3Impl.minDistance(wires)
        assertEquals(expectedResult, minDistance)
    }

    @Test
    fun part1Result() {
        val input = parseInput()
        val minDistance = D3Impl.minDistance(input)
        assertEquals(1064, minDistance)
    }

    @ParameterizedTest(name = "{0}, {1} -> {2}")
    @CsvSource(
            "R75,D30,R83,U83,L12,D49,R71,U7,L72 | U62,R66,U55,R34,D71,R55,D58,R83 | 610",
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51 | U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 | 410",
            delimiter = '|'
    )
    fun `part2 test cases`(in1: String, in2: String, expectedResult: Int) {
        val wire1 = in1.split(",")
        val wire2 = in2.split(",")
        val wires = listOf(wire1, wire2)
        val minSteps = D3Impl.minSteps(wires)
        assertEquals(expectedResult, minSteps)
    }

    
    // Po przejrzeniu wyników wydaje się że nie ma cross pointów nakładających się w tym samym miejscu
    @Test
    fun part2Result() {
        val input = parseInput()
        val minSteps = D3Impl.minSteps(input)
        assertEquals(25676, minSteps)
    }

    private fun parseInput(): List<List<String>> {
        return ParseUtils.readLinesFromResource("/2019/03/input.txt")
                .map { it.split(",") }
    }

}