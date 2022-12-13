package com.raphalsolarski.advent.y2022.d13

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2022.d09.D09
import com.raphalsolarski.advent.y2022.d13.D13.PacketDataInteger
import com.raphalsolarski.advent.y2022.d13.D13.PacketDataList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class D13Test {

    private val realInput = ParseUtils.readLinesFromResource("/2022/13/input.txt")

    private val exampleInput = listOf(
        "[1,1,3,1,1]",
        "[1,1,5,1,1]",
        "",
        "[[1],[2,3,4]]",
        "[[1],4]",
        "",
        "[9]",
        "[[8,7,6]]",
        "",
        "[[4,4],4,4]",
        "[[4,4],4,4,4]",
        "",
        "[7,7,7,7]",
        "[7,7,7]",
        "",
        "[]",
        "[3]",
        "",
        "[[[]]]",
        "[[]]",
        "",
        "[1,[2,[3,[4,[5,6,7]]]],8,9]",
        "[1,[2,[3,[4,[5,6,0]]]],8,9]",
    )

    @Test
    internal fun testParse() {
        val lines = listOf(
            "[[1],[2,3,4]]",
            "[[1],4]",
            "",
        )
        val output = D13.parse(lines)

        assertEquals(
            listOf(
                Pair(
                    PacketDataList(
                        listOf(
                            PacketDataList(listOf(PacketDataInteger(1))),
                            PacketDataList(listOf(PacketDataInteger(2), PacketDataInteger(3), PacketDataInteger(4)))
                        )
                    ),
                    PacketDataList(listOf(PacketDataList(listOf(PacketDataInteger(1))), PacketDataInteger(4)))
                )
            ),
            output
        )
    }

    @Test
    fun star1examplesTest() {
        assertEquals(13, D09.simulateAndCountVisitedByTail(exampleInput, tailSize = 1))
    }

    @Test
    fun star1realTest() {
        assertEquals(6256, D09.simulateAndCountVisitedByTail(realInput, tailSize = 1))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(1, D09.simulateAndCountVisitedByTail(exampleInput, tailSize = 9))
    }

    @Test
    fun star2realTest() {
        assertEquals(2665, D09.simulateAndCountVisitedByTail(realInput, tailSize = 9))
    }

}