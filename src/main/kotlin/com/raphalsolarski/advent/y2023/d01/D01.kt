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
        val first = line.find { c -> c.isDigit() }
        val last = line.last { c -> c.isDigit() }
        return "$first$last".toInt()
    }

    fun findAndCombineNumbersS2(line: String): Int {
        val mappedLine = mapPhrasesToDigits(line)
        val first = mappedLine.find { c -> c.isDigit() }
        val last = mappedLine.last { c -> c.isDigit() }
        return "$first$last".toInt()
    }

    private fun mapPhrasesToDigits(line: String): String {
        var acc = line
        while(true) {
            val found = acc.findAnyOf(numbers.keys) ?: break
            acc = acc.replaceFirst(found.second, numbers[found.second].toString())
        }
        return acc
    }

}
