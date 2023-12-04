package com.raphalsolarski.advent.y2023.d04

import java.util.regex.Pattern

object D04 {

    fun computeTotalPoints(lines: List<String>): Int {
        return lines
            .map(::parseLineToScratchCard)
            .sumOf { it.computePoints() }
    }

    fun parseLineToScratchCard(line: String): ScratchCard {
        val (cardIdRaw, numbersRaw) = line.split(": ")
        val cardId = cardIdRaw.removePrefix("Card").trim().toInt()
        val (winningNumbersRaw, selectedNumbersRaw) = numbersRaw.split("|")

        fun parseNumbers(numbersRaw: String): List<Int> {
            return numbersRaw.trim().split(Pattern.compile("\\s+")).map { it.trim().toInt() }
        }

        return ScratchCard(cardId, parseNumbers(winningNumbersRaw), parseNumbers(selectedNumbersRaw))
    }

    data class ScratchCard(val id: Int, val winningNumbers: List<Int>, val selectedNumbers: List<Int>) {
        fun computePoints(): Int {
            val commonNumbers = winningNumbers.toSet().intersect(selectedNumbers.toSet())
            return if (commonNumbers.isEmpty()) {
                0
            } else {
                1 * pow(2, commonNumbers.size - 1)
            }
        }

        private fun pow(number: Int, exponent: Int): Int {
            if (exponent == 0) {
                return 1
            }
            var acc = number
            IntRange(2, exponent).forEach { _ ->
                acc *= number
            }
            return acc
        }
    }
}
