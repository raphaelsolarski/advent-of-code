package com.raphalsolarski.advent.y2023.d01

object D01 {

    private val numbers = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    fun findAndCombineNumbers(line: String): Int {
        val first = line.first(Char::isDigit)
        val last = line.last(Char::isDigit)
        return combineTwoNumbers(first, last)
    }

    fun findAndCombineNumbersS2(line: String): Int {
        val mappedLine = mapPhrasesToDigits(line)
        val first = mappedLine.first(Char::isDigit)
        val last = mappedLine.last(Char::isDigit)
        return combineTwoNumbers(first, last)
    }

    private fun combineTwoNumbers(first: Char, last: Char) = "$first$last".toInt()

    private fun mapPhrasesToDigits(line: String): String {
        var acc = line
        while (true) {
            val found = acc.findAnyOf(numbers.keys) ?: break
            acc = acc.replaceFirst(found.second, numbers[found.second].toString())
        }
        return acc
    }

}
