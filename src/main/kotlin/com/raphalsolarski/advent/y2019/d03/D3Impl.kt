package com.raphalsolarski.advent.y2019.d03

import java.lang.IllegalStateException
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class D3Impl {
    companion object {
        fun minDistance(wires: List<List<String>>): Int {
            val crossPoints = findCrossPoints(wires)
            var minDistance = 0
            for (point in crossPoints) {
                val computedDistance = computeDistance(point)
                minDistance = if (minDistance == 0) {
                    computedDistance
                } else {
                    min(minDistance, computedDistance)
                }
            }
            return minDistance
        }

        fun minSteps(wires: List<List<String>>): Int {
            val crossPoints = findCrossPoints(wires)
            var minDistance = 0
            for (point in crossPoints) {
                val computedCost = point.steps1 + point.steps2
                minDistance = if (minDistance == 0) {
                    computedCost
                } else {
                    min(minDistance, computedCost)
                }
            }
            return minDistance
        }

        private fun computeDistance(point: CrossPoint): Int {
            return abs(point.point.first) + abs(point.point.second)
        }

        fun findCrossPoints(wires: List<List<String>>): List<CrossPoint> {
            val stats = MapStats(0, 0, 0, 0)
            val map = mutableMapOf<Pair<Int, Int>, MapPoint>()
            map[Pair(0, 0)] = MapPoint(listOf())
            var wireId = 0
            for (wire in wires) {
                drawWire(wire, map, stats, wireId)
                wireId++
            }
//            printMap(map, stats)
            return stats.getCrossPoints()
        }

        private fun drawWire(wire: List<String>, map: MutableMap<Pair<Int, Int>, MapPoint>, mapStats: MapStats, wireId: Int) {
            var currentPoint: Pair<Int, Int> = Pair(0, 0)
            var previousCost = Box(0)
            var nextPoint: Pair<Int, Int>
            var pointsToDraw: List<Pair<Int, Int>>
            for (route in wire) {
                val direction = route[0]
                val amount = route.substring(1).toInt()
                if (direction == 'U') {
                    nextPoint = currentPoint.copy(second = currentPoint.second + amount)
                    pointsToDraw = ((currentPoint.second + 1)..nextPoint.second).toList().map { Pair(currentPoint.first, it) }
                } else if (direction == 'R') {
                    nextPoint = currentPoint.copy(first = currentPoint.first + amount)
                    pointsToDraw = ((currentPoint.first + 1)..nextPoint.first).toList().map { Pair(it, currentPoint.second) }
                } else if (direction == 'D') {
                    nextPoint = currentPoint.copy(second = currentPoint.second - amount)
                    pointsToDraw = ((currentPoint.second - 1).downTo(nextPoint.second)).toList().map { Pair(currentPoint.first, it) }
                } else if (direction == 'L') {
                    nextPoint = currentPoint.copy(first = currentPoint.first - amount)
                    pointsToDraw = ((currentPoint.first - 1).downTo(nextPoint.first)).toList().map { Pair(it, currentPoint.second) }
                } else {
                    throw IllegalStateException("Unknown direction $direction")
                }
                drawPoints(pointsToDraw, map, direction, mapStats, wireId, previousCost)
                currentPoint = nextPoint
            }
        }

        private fun drawPoints(points: List<Pair<Int, Int>>,
                               map: MutableMap<Pair<Int, Int>, MapPoint>,
                               direction: Char,
                               mapStats: MapStats,
                               wireId: Int,
                               previousCost: Box<Int>) {
            for (point in points) {
                mapStats.update(point)
                previousCost.v = previousCost.v + 1
                val current = map[point]
                if (current == null) {
                    map[point] = MapPoint(listOf(WirePoint(wireId, previousCost.v)))
//                    val symbol = if (current == null && (direction == 'U' || direction == 'D')) {
//                        wireId
//                    } else if (current == null && (direction == 'R' || direction == 'L')) {
//                        wireId
//                    }
                } else {
                    val differentWireWirePoints = current.wirePoints.filter { it.wireId != wireId }
                    differentWireWirePoints.forEach { mapStats.addCrossPoint(CrossPoint(point, it.stepsCost, previousCost.v)) }
                    map[point] = current.copy(wirePoints = current.wirePoints.plus(WirePoint(wireId, previousCost.v)))
                }
//                map[point] = symbol
//                if (symbol == "X") {
//                    mapStats.addCrossPoint(CrossPoint(point, 0, 0))
//                }
            }
        }

        private fun printMap(map: Map<Pair<Int, Int>, String>, mapStats: MapStats): Unit {
            for (x in mapStats.maxX downTo mapStats.minX) {
                for (y in mapStats.minY..mapStats.maxY) {
                    val point = Pair(x, y)
                    print(map.getOrDefault(point, "."))
                }
                println()
            }
        }
    }

    class MapStats(var maxX: Int, var maxY: Int, var minX: Int, var minY: Int) {
        private val crossPoints = mutableListOf<CrossPoint>()

        fun update(point: Pair<Int, Int>) {
            val x = point.first
            val y = point.second
            maxX = max(x, maxX)
            maxY = max(y, maxY)
            minX = min(x, minX)
            minY = min(y, minY)
        }

        fun addCrossPoint(point: CrossPoint) {
            crossPoints.add(point)
        }

        fun getCrossPoints(): List<CrossPoint> {
            return crossPoints
        }
    }

    data class CrossPoint(val point: Pair<Int, Int>, val steps1: Int, val steps2: Int) : Comparable<CrossPoint> {
        fun sum(): Int {
            return steps1 + steps2
        }

        override fun compareTo(other: CrossPoint): Int {
            val compareTo = this.point.first.compareTo(other.point.first)
            return if (compareTo != 0) {
                compareTo
            } else {
                this.point.second.compareTo(other.point.second)
            }
        }
    }

    data class MapPoint(val wirePoints: List<WirePoint>)
    data class WirePoint(val wireId: Int, val stepsCost: Int)
    class Box<V>(var v: V)
}