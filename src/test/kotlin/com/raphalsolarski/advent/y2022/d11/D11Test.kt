package com.raphalsolarski.advent.y2022.d11

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2022.d11.D11.Monkey
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class D11Test {

    private val realInput = run {
        fun parseOperation(string: String): (Long) -> Long {
            val split = string.split(" ")
            val op1 = split[0]
            val operator = split[1]
            val op2 = split[2]
            return {old: Long ->
                val arg1 = if (op1 == "old") old else op1.toLong()
                val arg2 = if (op2 == "old") old else op2.toLong()
                if (operator == "*") {
                    arg1 * arg2
                } else {
                    arg1 + arg2
                }
            }
        }

        val rawLines = ParseUtils.readLinesFromResource("/2022/11/input.txt", withEmptyLines = true)
        val emptyLinesIndexes = rawLines.withIndex().filter { it.value == "" }.map { it.index }
        val monkeys = mutableListOf<Monkey>()
        for (endLineIndex in emptyLinesIndexes) {
            val items = rawLines[endLineIndex - 5].substring(18).split(", ").map { it.toLong() }.toMutableList()
            val operationString = rawLines[endLineIndex - 4].substring(19)
            val divisibleByTest = rawLines[endLineIndex - 3].substring(21).toLong()
            val ifTrueMonkey = rawLines[endLineIndex - 2].substring(29).toInt()
            val ifFalseMonkey = rawLines[endLineIndex - 1].substring(30).toInt()
            monkeys.add(
                Monkey(
                    items,
                    parseOperation(operationString),
                    divisibleByTest,
                    ifTrueMonkey,
                    ifFalseMonkey
                )
            )
        }
        monkeys
    }

    private val exampleMonkeys = listOf(
        Monkey(
            mutableListOf(79, 98),
            { it * 19 },
            23,
            2,
            3
        ),
        Monkey(
            mutableListOf(54, 65, 75, 74),
            { it + 6 },
            19L,
            2,
            0
        ),
        Monkey(
            mutableListOf(79, 60, 97),
            { it * it },
            13L,
            1,
            3
        ),
        Monkey(
            mutableListOf(74),
            { it + 3 },
            17L,
            0,
            1
        )
    )

    @Test
    fun star1examplesTest() {
        assertEquals(10605, D11.stats(exampleMonkeys, 20))
    }

    @Test
    fun star1realTest() {
        assertEquals(95472, D11.stats(realInput, 20))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(2713310158, D11.stats(exampleMonkeys, 10000, decreaser = 1L))
    }

    @Test
    fun star2realTest() {
        assertEquals(17926061332, D11.stats(realInput, 10000, decreaser = 1L))
    }
}
