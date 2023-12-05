package com.raphalsolarski.advent.y2023.d05

object D05 {

    fun parseInput(lines: List<String>): Almanac {
        val seeds = lines[0].removePrefix("seeds:").trim().split(" ").map { it.toLong() }

        val emptyLines = lines.withIndex().filter { it.value == "" }

        val maps = emptyLines.windowed(2, 1) { window ->
            lines.subList(window[0].index, window[1].index)
        }
            .map { mapRaw ->
                val mapName = mapRaw[1].removeSuffix(" map:")
                val ranges = mapRaw.subList(2, mapRaw.size).map { mappingLine ->
                    val (destStart, sourceStart, size) = mappingLine.split(" ").map { it.toLong() }
                    CategoryRange(sourceStart, destStart, size)
                }
                CategoryMap(mapName, ranges)
            }.associateBy { it.name }

        return Almanac(seeds, maps)
    }

    fun findLowestLocationNumber(input: List<String>): Long {
        return parseInput(input)
            .seeds
            .minOf { computeLocationNumberForSeed(it, parseInput(input)) }
    }

    private fun computeLocationNumberForSeed(seed: Long, almanac: Almanac): Long {
        return almanac.maps.values.fold(seed) { value, map ->
            map.mapValue(value)
        }
    }

    data class Almanac(val seeds: List<Long>, val maps: Map<String, CategoryMap>)

    data class CategoryMap(val name: String, val ranges: List<CategoryRange>) {
        fun mapValue(value: Long): Long {
            return ranges.find { it.sourceRange.contains(value) }?.map(value) ?: value
        }
    }

    data class CategoryRange(val sourceStart: Long, val destinationStart: Long, val length: Long) {
        val sourceRange = LongRange(sourceStart, sourceStart + length - 1)
        fun map(value: Long): Long {
            return destinationStart + (value - sourceStart)
        }
    }
}
