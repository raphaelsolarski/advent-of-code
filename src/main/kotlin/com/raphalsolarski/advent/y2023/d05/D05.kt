package com.raphalsolarski.advent.y2023.d05

import java.util.TreeMap
import java.util.stream.StreamSupport

object D05 {

    fun parseInput(lines: List<String>): Almanac {
        val seeds = lines[0].removePrefix("seeds:").trim().split(" ").map { it.toLong() }.windowed(2, 2)
            .map { LongRange(it[0], it[0] + it[1]) }

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
        val almanac = parseInput(input)
        return almanac
            .seeds
            .parallelStream()
            .flatMap {
                StreamSupport.stream(
                    it.spliterator(),
                    true
                )
            }
            .map { computeLocationNumberForSeed(it, almanac) }
            .min { o1, o2 -> o1.compareTo(o2) }
            .orElseThrow()
    }

    private fun computeLocationNumberForSeed(seed: Long, almanac: Almanac): Long {
        return almanac.mapsValues.fold(seed) { value, map ->
            map.mapValue(value)
        }
    }

    data class Almanac(val seeds: List<LongRange>, val maps: Map<String, CategoryMap>) {
        val mapsValues = maps.values.toList()
    }

    data class CategoryMap(val name: String, val ranges: List<CategoryRange>) {

        private val internalMap: TreeMap<Long, CategoryRange> = run {
            val treeMap = TreeMap<Long, CategoryRange>()
            ranges.forEach { treeMap[it.sourceStart] = it }
            treeMap
        }

        fun mapValue(value: Long): Long {
            //todo: optimize?
            val found = internalMap.floorEntry(value)?.value
            return if (found != null && found.sourceRange.contains(value)) {
                found.map(value)
            } else {
                value
            }
        }
    }

    data class CategoryRange(val sourceStart: Long, val destinationStart: Long, val length: Long) {
        val sourceRange = LongRange(sourceStart, sourceStart + length - 1)
        private val shift = destinationStart - sourceStart
        fun map(value: Long): Long {
            return shift + value
        }
    }
}
