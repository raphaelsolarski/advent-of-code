package com.raphalsolarski.advent.y2023.d02

object D02 {
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

    data class Game(val id: Int, val showedSets: List<CubeSet>)
    data class CubeSet(val red: Int, val green: Int, val blue: Int)
}
