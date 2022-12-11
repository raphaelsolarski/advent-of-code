package com.raphalsolarski.advent.y2022.d11

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2022.d11.D11.Monkey
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

internal class D11Test {

    private val realInput = run {
        fun parseOperation(string: String): (UsedCompType) -> UsedCompType {
            val split = string.split(" ")
            val op1 = split[0]
            val operator = split[1]
            val op2 = split[2]
            return {old: UsedCompType ->
                val arg1 = if (op1 == "old") old else BigInteger.valueOf(op1.toLong())
                val arg2 = if (op2 == "old") old else BigInteger.valueOf(op2.toLong())
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
            val items = rawLines[endLineIndex - 5].substring(18).split(", ").map { it.toBigInteger() }.toMutableList()
            val operationString = rawLines[endLineIndex - 4].substring(19)
            val divisibleByTest = rawLines[endLineIndex - 3].substring(21).toBigInteger()
            val ifTrueMonkey = rawLines[endLineIndex - 2].substring(29).toInt()
            val ifFalseMonkey = rawLines[endLineIndex - 1].substring(30).toInt()
            monkeys.add(
                Monkey(
                    items,
                    parseOperation(operationString),
                    { it % divisibleByTest == BigInteger.ZERO },
                    ifTrueMonkey,
                    ifFalseMonkey
                )
            )
        }
        monkeys
    }

    private val exampleMonkeys = listOf(
        Monkey(
            mutableListOf(79, 98).map { it.toBigInteger() }.toMutableList(),
            { it * BigInteger.valueOf(19) },
            { it % BigInteger.valueOf(23) == BigInteger.ZERO },
            2,
            3
        ),
        Monkey(
            mutableListOf(54, 65, 75, 74).map { it.toBigInteger() }.toMutableList(),
            { it + BigInteger.valueOf(6) },
            { it % BigInteger.valueOf(19L) == BigInteger.ZERO },
            2,
            0
        ),
        Monkey(
            mutableListOf(79, 60, 97).map { it.toBigInteger() }.toMutableList(),
            { it * it },
            { it % BigInteger.valueOf(13L) == BigInteger.ZERO },
            1,
            3
        ),
        Monkey(
            mutableListOf(74).map { it.toBigInteger() }.toMutableList(),
            { it + BigInteger.valueOf(3) },
            { it % BigInteger.valueOf(17L) == BigInteger.ZERO },
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
        assertEquals(2713310158, D11.stats(exampleMonkeys, 10000, decreaser = BigInteger.valueOf(1L)))
    }

    @Test
    fun star2realTest() {
        TODO()
    //        assertEquals(6256, D11.stats(realInput, 10000))
    }
}
