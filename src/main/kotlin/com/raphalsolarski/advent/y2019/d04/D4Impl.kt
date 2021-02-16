package com.raphalsolarski.advent.y2019.d04

class D4Impl {
    companion object {
        const val inputMin: Int = 272091
        const val inputMax: Int = 815432
        val range: IntRange = IntRange(inputMin, inputMax)

        fun testRange(testFunc: (Int) -> Boolean): Int {
            return range.filter { testFunc(it) }.count()
        }

        fun testPassword(password: Int): Boolean {
            val digits = passwordDigits(password)
            return containsAdjacentNumbers(digits) && notDecreasingNumbers(digits)
        }

        fun testPasswordPart2(password: Int): Boolean {
            val digits = passwordDigits(password)
            return containsExact2AdjacentNumbers(digits) && notDecreasingNumbers(digits)
        }

        private fun passwordDigits(password: Int) = password.toString().toList().map { it.toString().toInt() }

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

        private fun containsExact2AdjacentNumbers(digits: List<Int>): Boolean {
            val (_, result, counter) = digits.subList(1, digits.lastIndex + 1).fold(Triple(digits[0], false, 1), { acc, elem ->
                val (previous, hasExact2Adjacent, counter) = acc
                if (hasExact2Adjacent)
                    Triple(elem, true, 0)
                else
                    if (previous != elem && counter == 2)
                        Triple(elem, true, 0)
                    else if (previous != elem && counter != 2)
                        Triple(elem, false, 1)
                    else
                        Triple(elem, false, counter + 1)
            })
            return result || counter == 2
        }

    }
}