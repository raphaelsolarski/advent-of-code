package com.raphalsolarski.advent.y2022.d05

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class D05Test {

    private val exampleInputStacks = listOf(
        listOf('N', 'Z'),
        listOf('D', 'C', 'M'),
        listOf('P')
    )

    private val exampleInputProcedure = listOf(
        "move 1 from 2 to 1",
        "move 3 from 1 to 3",
        "move 2 from 2 to 1",
        "move 1 from 1 to 2"
    )
//                [M]     [W] [M]
//            [L] [Q] [S] [C] [R]
//            [Q] [F] [F] [T] [N] [S]
//    [N]     [V] [V] [H] [L] [J] [D]
//    [D] [D] [W] [P] [G] [R] [D] [F]
//[T] [T] [M] [G] [G] [Q] [N] [W] [L]
//[Z] [H] [F] [J] [D] [Z] [S] [H] [Q]
//[B] [V] [B] [T] [W] [V] [Z] [Z] [M]


    private val realStacks: List<List<Char>> = listOf(
        "TZB",
        "NDTHV",
        "DMFB",
        "LQVWGJT",
        "MQFVPGDW",
        "SFHGQZV",
        "WCTLRNSZ",
        "MRNJDWHZ",
        "SDFLQM",
    ).map { it.toList() }

    private val realInput = ParseUtils.readLinesFromResource("/2022/05/input.txt")

    @Test
    fun star1examplesTest() {
        assertEquals(
            "CMZ",
            D05.topsAfterProcedure(exampleInputStacks, exampleInputProcedure).joinToString(separator = "")
        )
    }

    @Test
    fun star1realTest() {
        assertEquals("NTWZZWHFV", D05.topsAfterProcedure(realStacks, realInput).joinToString(separator = ""))
    }

    @Test
    fun star2examplesTest() {
        assertEquals(
            "MCD",
            D05.topsAfterProcedureV2(exampleInputStacks, exampleInputProcedure).joinToString(separator = "")
        )
    }

    @Test
    fun star2realTest() {
        assertEquals("BRZGFVBTJ", D05.topsAfterProcedureV2(realStacks, realInput).joinToString(separator = ""))
    }

}