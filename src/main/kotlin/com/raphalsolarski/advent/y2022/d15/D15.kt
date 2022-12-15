package com.raphalsolarski.advent.y2022.d15

import kotlin.math.abs

object D15 {

    fun countUnavailablePointsInRow(rowIndex: Long, sensorsWithClosestBeaconsRaw: List<String>): Int {
        val sensorsWithBeacons = parse(sensorsWithClosestBeaconsRaw)

        val knownBeaconsPositions = sensorsWithBeacons.map { it.beacon }.toSet()
        val sensors = sensorsWithBeacons.map { it.sensor }.toSet()

        val inSensorRangePositionsInRow = mutableSetOf<Long>()
        sensorsWithBeacons.forEach {
            val (sensor, beacon) = it
            val range = sensor.manhattanDistance(beacon)
            inSensorRangePositionsInRow.addAll(sensor.columnsInRangeInRow(range, rowIndex))
        }

        inSensorRangePositionsInRow.removeAll(sensors.filter { it.row == rowIndex }.map { it.column }.toSet())
        inSensorRangePositionsInRow.removeAll(knownBeaconsPositions.filter { it.row == rowIndex }.map { it.column }.toSet())
        return inSensorRangePositionsInRow.size
    }



    fun parse(lines: List<String>): List<SensorWithClosestBeacon> {
        return lines.map { parseLine(it) }
    }

    fun parseLine(line: String): SensorWithClosestBeacon {
        val sensorPart = line.split(": ")[0].substring(10)
        val beaconPart = line.split(": ")[1].substring(21)

        val sensor = Point(sensorPart.split(", ")[0].substring(2).toLong(), sensorPart.split(", ")[1].substring(2).toLong())
        val beacon = Point(beaconPart.split(", ")[0].substring(2).toLong(), beaconPart.split(", ")[1].substring(2).toLong())
        return SensorWithClosestBeacon(sensor, beacon)
    }

    data class Point(val column: Long, val row: Long) {
        fun manhattanDistance(targetPoint: Point): Long {
            return abs(column - targetPoint.column) + abs(row - targetPoint.row)
        }

        fun columnsInRangeInRow(range: Long, rowIndex: Long): Set<Long> {
            // column - distance + 1 .. column + distance - 1 // when in row of point -1
            // column - distance .. column + distance // when in row of point
            // column - distance + 1 .. column + distance - 1 // when in row of point +1

            val distanceInRowsFromRow = abs(row - rowIndex)
            if (distanceInRowsFromRow > range) {
                return emptySet()
            }
            return ((column - range + distanceInRowsFromRow) ..  (column + range - distanceInRowsFromRow))
                .toSet()
        }
    }

    data class SensorWithClosestBeacon(val sensor: Point, val beacon: Point)
}