package com.raphalsolarski.advent.y2019.d01


class FuelCalculator {
    companion object {
        fun compute(amount: Long): Long {
            return amount / 3 - 2
        }

        fun compute2(amount: Long): Long {
            var sum: Long = 0
            var lastResult: Long = amount
            while (true) {
                lastResult = lastResult / 3 - 2
                if (lastResult > 0L) {
                    sum += lastResult
                } else {
                    break
                }
            }
            return sum
        }
    }
}