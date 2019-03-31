package com.raphaelsolarski

object Captcha {
    fun computeComparingToNext(numbers: List<Int>): Int {
        return sumElementsComparing(numbers, this::computeNextIndex)
    }

    fun computeHalfway(numbers: List<Int>): Int {
        return sumElementsComparing(numbers, this::computeHalfwayIndex)
    }

    private fun sumElementsComparing(numbers: List<Int>, elementToCompareIndexExtractor: (Int, Int) -> Int): Int {
        var sum = 0
        numbers.forEachIndexed { index, i ->
            val elementToCompareIndex = elementToCompareIndexExtractor(index, numbers.size)
            if (i == numbers[elementToCompareIndex]) {
                sum += i
            }
        }
        return sum
    }

    private fun computeNextIndex(pos: Int, len: Int): Int {
        return (pos + 1) % len
    }

    private fun computeHalfwayIndex(pos: Int, len: Int): Int {
        val shift = len / 2
        return (pos + shift) % len
    }
}
