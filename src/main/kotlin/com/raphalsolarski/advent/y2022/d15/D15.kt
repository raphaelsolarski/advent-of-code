package com.raphalsolarski.advent.y2022.d15

import kotlin.math.abs
import kotlin.math.max

object D15 {

    fun findTuningFrequency(sensorsWithClosestBeaconsRaw: List<String>, maxColumns: Long, maxRows: Long): Long? {
        val sensorsWithBeacons = parse(sensorsWithClosestBeaconsRaw)

        for (row in 0..maxRows) {
            val sensorRangesInRow = mutableListOf<LongRange>()

            sensorsWithBeacons.forEach {
                val (sensor, beacon) = it
                val range = sensor.manhattanDistance(beacon)
                sensorRangesInRow.add(sensor.columnsInRangeInRow(range, row))
            }
            val lackingColumnInRow = findLackInRow(sensorRangesInRow)
            if (lackingColumnInRow != null) {
                return lackingColumnInRow * 4000000 + row
            }
        }
        return null
    }

    private fun findLackInRow(rowRanges: List<LongRange>): Long? {
        val sumRanges = sumRanges(rowRanges)
        return if (sumRanges.size > 1) {
            sumRanges[0].last + 1
        } else {
            null
        }
    }

    private fun sumRanges(ranges: List<LongRange>): List<LongRange> {
        val sortedByFirstAndLength = ranges.sortedWith(compareBy<LongRange> { it.first }.thenBy { it.last - it.first })
        return sortedByFirstAndLength.fold(emptyList()) { acc, currentRange ->
            if (acc.isNotEmpty()) {
                val prevRange = acc.last()
                if (prevRange.last >= currentRange.first) {
                    val accumulatedRange = LongRange(prevRange.first, max(prevRange.last, currentRange.last))
                    acc.minusElement(prevRange).plusElement(accumulatedRange)
                } else {
                    acc.plusElement(currentRange)
                }
            } else {
                listOf(currentRange)
            }
        }
    }

    fun parse(lines: List<String>): List<SensorWithClosestBeacon> {
        return lines.map { parseLine(it) }
    }

    fun parseLine(line: String): SensorWithClosestBeacon {
        val sensorPart = line.split(": ")[0].substring(10)
        val beaconPart = line.split(": ")[1].substring(21)

        val sensor =
            Point(sensorPart.split(", ")[0].substring(2).toLong(), sensorPart.split(", ")[1].substring(2).toLong())
        val beacon =
            Point(beaconPart.split(", ")[0].substring(2).toLong(), beaconPart.split(", ")[1].substring(2).toLong())
        return SensorWithClosestBeacon(sensor, beacon)
    }

    data class Point(val column: Long, val row: Long) {
        fun manhattanDistance(targetPoint: Point): Long {
            return abs(column - targetPoint.column) + abs(row - targetPoint.row)
        }

        fun columnsInRangeInRow(range: Long, rowIndex: Long): LongRange {
            // column - distance + 1 .. column + distance - 1 // when in row of point -1
            // column - distance .. column + distance // when in row of point
            // column - distance + 1 .. column + distance - 1 // when in row of point +1

            val distanceInRowsFromRow = abs(row - rowIndex)
            if (distanceInRowsFromRow > range) {
                return LongRange.EMPTY
            }
            return (column - range + distanceInRowsFromRow)..(column + range - distanceInRowsFromRow)
        }
    }

    data class SensorWithClosestBeacon(val sensor: Point, val beacon: Point)
}