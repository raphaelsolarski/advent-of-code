package com.raphalsolarski.advent.y2022.d15

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2022.d15.D15.Point
import com.raphalsolarski.advent.y2022.d15.D15.SensorWithClosestBeacon
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D15Test {
    private val realInput = ParseUtils.readLinesFromResource("/2022/15/input.txt", withEmptyLines = false)

    private val exampleInput = listOf(
        "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
        "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
        "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
        "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
        "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
        "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
        "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
        "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
        "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
        "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
        "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
        "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
        "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
        "Sensor at x=20, y=1: closest beacon is at x=15, y=3",
    )

    @Test
    fun parseTest() {
        assertEquals(SensorWithClosestBeacon(Point(2, 18), Point(-2, 15)), D15.parseLine("Sensor at x=2, y=18: closest beacon is at x=-2, y=15"))
    }


    @Test
    fun star1examplesTest() {
        assertEquals(56000011, D15.findTuningFrequency(exampleInput, 20, 20))
    }

    @Test
    fun star1realTest() {
        assertEquals(11379394658764, D15.findTuningFrequency(realInput, 4000000, 4000000))
    }
}