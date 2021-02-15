package com.raphalsolarski.advent.y2019.d04

class D4Impl {
    companion object {
        const val part1InputMin: Int = 272091
        const val part1InputMax: Int = 815432

        fun testRange(range: IntRange): Int {
            return range.filter { testPassword(it) }.count()
        }

        fun testPassword(password: Int): Boolean {
            val digits = password.toString().toList().map { it.toString().toInt() }
            return containsAdjacentNumbers(digits) && notDecreasingNumbers(digits)
        }

        private fun containsAdjacentNumbers(digits: List<Int>): Boolean {
            val (_, result) = digits.subList(1, digits.lastIndex + 1).fold((digits[0] to false), { acc, elem ->
                val (previous, hasAdjacent) = acc
                if (hasAdjacent)
                    elem to true
                else
                    elem to (previous == elem)
            })
            return result
        }

        private fun notDecreasingNumbers(digits: List<Int>): Boolean {
            val (_, result) = digits.subList(1, digits.lastIndex + 1).fold((digits[0] to true), { acc, elem ->
                val (previous, notDecreasing) = acc
                if (notDecreasing)
                    elem to (previous <= elem)
                else
                    elem to false
            })
            return result
        }

    }
}