package com.raphalsolarski.advent.y2023.d05

object D05 {

    fun parseInput(lines: List<String>): Almanac {
        val seeds = lines[0].removePrefix("seeds:").trim().split(" ").map { it.toInt() }

        val emptyLines = lines.withIndex().filter { it.value == "" }

        val maps = emptyLines.windowed(2, 1) { window ->
            lines.subList(window[0].index, window[1].index)
        }
            .map { mapRaw ->
                val mapName = mapRaw[1].removeSuffix(" map:")
                val ranges = mapRaw.subList(2, mapRaw.size).map {
                    val (destStart, sourceStart, size) = it.split(" ").map { it.toInt() }
                    CategoryRange(sourceStart, destStart, size)
                }
                CategoryMap(mapName, ranges)
            }.associateBy { it.name }

        return Almanac(seeds, maps)
    }

    data class Almanac(val seeds: List<Int>, val maps: Map<String, CategoryMap>)

    data class CategoryMap(val name: String, val ranges: List<CategoryRange>)

    data class CategoryRange(val sourceStart: Int, val destinationStart: Int, val length: Int)
}
