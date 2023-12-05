package com.raphalsolarski.advent.y2023.d04

import java.util.regex.Pattern

object D04 {

    fun computeRemainingScratchCardsCount(lines: List<String>): Int {
        val originals = lines
            .map(::parseLineToScratchCard)

        return computeRemainingCardsOccurrences(originals).values.sum()
    }

    private fun computeRemainingCardsOccurrences(originals: List<ScratchCard>): Map<Int, Int> {
        val idToInstances = originals.associate { it.id to 1 }.toMutableMap()

        originals.forEach { currentScratchCard ->
            val commonNumbersCount = currentScratchCard.commonNumbers().size
            if (commonNumbersCount > 0) {
                val id = currentScratchCard.id
                val wonScratchCardsRange = IntRange(id + 1, minOf(id + commonNumbersCount, originals.size))
                val wonScratchCardsCount = idToInstances[id]!!

                wonScratchCardsRange.forEach { wonCardId ->
                    idToInstances[wonCardId] = (idToInstances[wonCardId]!! + wonScratchCardsCount)
                }
            }
        }
        return idToInstances
    }

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
            val commonNumbers = commonNumbers()
            return if (commonNumbers.isEmpty()) {
                0
            } else {
                1 * pow(2, commonNumbers.size - 1)
            }
        }

        fun commonNumbers() = winningNumbers.toSet().intersect(selectedNumbers.toSet())

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
