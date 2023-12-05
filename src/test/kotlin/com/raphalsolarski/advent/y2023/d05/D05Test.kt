package com.raphalsolarski.advent.y2023.d05

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2023.d05.D05.CategoryRange
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class D05Test {

    private val exampleInput = listOf(
        "seeds: 79 14 55 13",
        "",
        "seed-to-soil map:",
        "50 98 2",
        "52 50 48",
        "",
        "soil-to-fertilizer map:",
        "0 15 37",
        "37 52 2",
        "39 0 15",
        "",
        "fertilizer-to-water map:",
        "49 53 8",
        "0 11 42",
        "42 0 7",
        "57 7 4",
        "",
        "water-to-light map:",
        "88 18 7",
        "18 25 70",
        "",
        "light-to-temperature map:",
        "45 77 23",
        "81 45 19",
        "68 64 13",
        "",
        "temperature-to-humidity map:",
        "0 69 1",
        "1 0 69",
        "",
        "humidity-to-location map:",
        "60 56 37",
        "56 93 4",
        ""
    )

    private val realInput = ParseUtils.readLinesFromResource("/2023/05/input.txt", withEmptyLines = true)

    @Test
    fun parseTest() {
        val almanac = D05.parseInput(exampleInput)
        assertEquals(listOf(79, 14, 55, 13), almanac.seeds)
        assertEquals(
            listOf(
                CategoryRange(56, 60, 37),
                CategoryRange(93, 56, 4)
            ),
            almanac.maps["humidity-to-location"]!!.ranges
        )
    }

//    @Test
//    fun exampleS1() {
//        assertEquals(35, D05.findLowestLocationNumber(exampleInput))
//    }
//
//    @Test
//    fun realS1() {
//        assertEquals(35, D05.findLowestLocationNumber(realInput))
//    }

    @Test
    fun exampleS2() {
        assertEquals(46, D05.findLowestLocationNumber(exampleInput))
    }

    @Test
    fun realS2() {
        assertEquals(35, D05.findLowestLocationNumber(realInput))
    }

}
