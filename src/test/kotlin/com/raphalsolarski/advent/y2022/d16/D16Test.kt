package com.raphalsolarski.advent.y2022.d16

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2022.d15.D15
import com.raphalsolarski.advent.y2022.d16.D16.Valve
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D16Test {
    private val realInput = ParseUtils.readLinesFromResource("/2022/15/input.txt", withEmptyLines = false)

    private val exampleInput = listOf(
        "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
        "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
        "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
        "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
        "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
        "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
        "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
        "Valve HH has flow rate=22; tunnel leads to valve GG",
        "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
        "Valve JJ has flow rate=21; tunnel leads to valve II",
    )

    @Test
    fun parseTest() {
        assertEquals(
            Valve("AA", 0, listOf("DD", "II", "BB")),
            D16.parseLine("Valve AA has flow rate=0; tunnels lead to valves DD, II, BB")
        )
        assertEquals(
            Valve("AA", 0, listOf("DD")),
            D16.parseLine("Valve AA has flow rate=0; tunnels lead to valve DD")
        )
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