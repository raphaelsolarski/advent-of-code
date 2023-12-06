package com.raphalsolarski.advent.y2023.d06

object D06 {

    fun computeResult(input: List<String>): Long {
        val (time, distance) = parseTimeToDistance(input)
        return countPossibleOptions(time, distance)
    }

    fun parseTimeToDistance(input: List<String>): Pair<Long, Long> {
        val time = input[0].removePrefix("Time:").replace(" ", "").toLong()
        val record = input[1].removePrefix("Distance:").replace(" ", "").toLong()
        return time to record
    }

    private fun countPossibleOptions(time: Long, record: Long): Long {
        var counter = 0L
        LongRange(0, time).forEach { timePress ->
            val remainingTime = time - timePress
            val distance = remainingTime * timePress
            if (distance > record) {
                counter++
            }
        }
        return counter
    }

}
