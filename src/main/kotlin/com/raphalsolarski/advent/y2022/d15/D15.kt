package com.raphalsolarski.advent.y2022.d15

import com.raphalsolarski.advent.y2022.d14.D14

object D15 {

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

    data class Point(val column: Long, val row: Long)

    data class SensorWithClosestBeacon(val sensor: Point, val beacon: Point)
}