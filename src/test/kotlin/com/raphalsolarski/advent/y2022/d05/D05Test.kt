package com.raphalsolarski.advent.y2022.d05

import com.raphalsolarski.advent.utils.ParseUtils
import com.raphalsolarski.advent.y2022.d04.D04
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class D05Test {
    internal class D04Test {

        private val exampleInputStacks = listOf<List<Char>>(
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
            assertEquals(listOf('C', 'M', 'Z'), D05.topsAfterProcedure(exampleInputStacks, exampleInputProcedure))
        }

        @Test
        fun star1realTest() {
            assertEquals(441, D05.topsAfterProcedure(realStacks, realInput).toString())
        }

        @Test
        fun star2examplesTest() {
            assertEquals(listOf('M', 'C', 'D'), D05.topsAfterProcedureV2(exampleInputStacks, exampleInputProcedure))
        }

        @Test
        fun star2realTest() {
            assertEquals(861, D05.topsAfterProcedureV2(realStacks, realInput).toString())
        }

    }
}