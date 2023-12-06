package com.raphalsolarski.advent.y2023.d06

import java.util.regex.Pattern

object D06 {

    fun computeResult(input: List<String>): Int {
        return parseTimeToDistance(input).map { countPossibleOptions(it.first, it.second) }
            .fold(1) { acc, element -> acc * element }
    }

    fun parseTimeToDistance(input: List<String>): List<Pair<Int, Int>> {
        val pattern = Pattern.compile("\\s+")
        val timeSplit = input[0].split(pattern)
        val recordSplit = input[1].split(pattern)
        return timeSplit
            .zip(recordSplit)
            .drop(1)
            .map { (time, record) ->
                time.toInt() to record.toInt()
            }
    }

    private fun countPossibleOptions(time: Int, record: Int): Int {
        var counter = 0
        IntRange(0, time).forEach { timePress ->
            val remainingTime = time - timePress
            val distance = remainingTime * timePress
            if (distance > record) {
                counter++
            }
        }
        return counter
    }

}
