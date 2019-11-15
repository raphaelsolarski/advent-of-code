package com.raphalsolarski.advent.y2018.d01

class Calculator {
    companion object {
        fun addAll(numbers: List<Int>): Int {
            return numbers.foldRight(0, { c, acc -> c + acc })
        }

        fun findFrequency(numbers: List<Int>): Int {
            val freqSet = mutableSetOf<Int>() // przy niemutowalnej wersji trwa to bardzo długo
            var freq = 0
            var tick = 0
            while (true) {
                val number = numbers[tick % numbers.size]
                freq += number
                if (freqSet.contains(freq)) {
                    return freq
                } else {
                    freqSet.add(freq)
                }
                tick++
            }
        }
    }
}
