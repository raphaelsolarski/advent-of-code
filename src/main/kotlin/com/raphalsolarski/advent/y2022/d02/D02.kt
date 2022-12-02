package com.raphalsolarski.advent.y2022.d02

class D02 {
    companion object {
        private val winsMap = mapOf(
            'A' to 'C',
            'B' to 'A',
            'C' to 'B'
        )

        private val losesMap = winsMap.map { it.value to it.key }.toMap()

        fun calculateScores(input: List<String>): Long {
            return input.map(::parseLine)
                .map(::calculateForSingleGame)
                .sumOf { it.toLong() }
        }

        private fun calculateForSingleGame(game: Pair<Char, Char>): Int {
            val (enemy, player) = game
            val normalizePlayerShape = normalizePlayerShape(player)
            val normalizedGame = enemy to normalizePlayerShape
            return fightPoints(normalizedGame) + shapePoints(normalizePlayerShape)
        }

        fun calculateScoresWay2(input: List<String>): Long {
            return input.map(::parseLine)
                .map(::calculateForSingleGameWay2)
                .sumOf { it.toLong() }
        }

        private fun parseLine(line: String): Pair<Char, Char> {
            return line[0] to line[2]
        }

        private fun calculateForSingleGameWay2(game: Pair<Char, Char>): Int {
            val (enemyShape, _) = game
            val playerShape = computePlayerShape(game)
            return fightPoints(enemyShape to playerShape) + shapePoints(playerShape)
        }

        private fun computePlayerShape(game: Pair<Char, Char>): Char {
            val (enemyShape, neededResult) = game

            return when (neededResult) {
                'X' -> {
                    //need to loose
                    winsMap.getValue(enemyShape)
                }
                'Y' -> {
                    //need to draw
                    enemyShape
                }
                else -> {
                    //need to win
                    losesMap.getValue(enemyShape)
                }
            }
        }

        private fun fightPoints(game: Pair<Char, Char>): Int {
            val (enemy, player) = game

            if (player == enemy) {
                return 3
            }

            val shapeAgainstWhichPlayerCanWin = winsMap.getValue(player)

            return if (enemy != shapeAgainstWhichPlayerCanWin) {
                0
            } else {
                6
            }
        }

        private fun normalizePlayerShape(shape: Char): Char {
            return mapOf(
                'X' to 'A',
                'Y' to 'B',
                'Z' to 'C'
            ).getValue(shape)
        }

        private fun shapePoints(shape: Char): Int {
            return mapOf(
                'A' to 1,
                'B' to 2,
                'C' to 3
            ).getValue(shape)
        }

    }
}
