package com.raphalsolarski.advent.y2023.d02

object D02 {
    private const val MAX_RED = 12
    private const val MAX_GREEN = 13
    private const val MAX_BLUE = 14

    fun parseLine(line: String): Game {
        val splits = line.split(": ")
        val id = splits[0].substring(5).toInt()

        val sets = splits[1].split("; ").map { setSplit ->
            val colorsCountsMap = setSplit.split(", ")
                .associate { it.split(" ")[1] to (it.split(" ")[0].toInt()) }
            CubeSet(
                colorsCountsMap.getOrElse("red") { 0 },
                colorsCountsMap.getOrElse("green") { 0 },
                colorsCountsMap.getOrElse("blue") { 0 }
            )
        }

        return Game(id, sets)
    }

    fun testPossibility(game: Game): Boolean {
        return game.showedSets.none { cubeSet ->
            cubeSet.red > MAX_RED
                    || cubeSet.green > MAX_GREEN
                    || cubeSet.blue > MAX_BLUE
        }
    }

    fun computeMinimalSetPower(game: Game): Int {
        return game.showedSets.fold(CubeSet(0, 0, 0)) { acc, currentSet ->
            currentSet.copy(
                red = maxOf(currentSet.red, acc.red),
                green = maxOf(currentSet.green, acc.green),
                blue = maxOf(currentSet.blue, acc.blue),
            )
        }.power()
    }

    data class Game(val id: Int, val showedSets: List<CubeSet>)
    data class CubeSet(val red: Int, val green: Int, val blue: Int) {
        fun power(): Int {
            return red * green * blue
        }
    }
}
