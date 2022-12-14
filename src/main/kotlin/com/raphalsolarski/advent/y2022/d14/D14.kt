package com.raphalsolarski.advent.y2022.d14

import java.lang.Integer.min
import kotlin.math.max

object D14 {

    private val sourcePoint = Point(500, 0)

    fun simulateAndCountSandInRestBeforeFallingIntoAbyss(rocksRaw: List<String>): Int {
        val rocks = parseRocks(rocksRaw)
        val sand = mutableSetOf<Point>()

        val rocksMaxRow = rocks.maxBy { it.row }.row
        var rocksMaxRowAchievedBySand = false
        while (!rocksMaxRowAchievedBySand) {
            var emittedSand = sourcePoint
            while (!rocksMaxRowAchievedBySand) {
                val possibleMoves = Direction.values()
                    .map { emittedSand.move(it) }
                    .filter { isPossibleMove(it, rocks, sand) }
                if (possibleMoves.isEmpty()) {
                    sand += emittedSand
                    break
                } else {
                    emittedSand = possibleMoves[0]
                }
                if (emittedSand.row == rocksMaxRow) {
                    rocksMaxRowAchievedBySand = true
                }
            }
        }
        return sand.size
    }

    private fun parseRocks(rocksRaw: List<String>): Set<Point> {
        fun fillRocksBetweenPointsIncluding(point1: Point, point2: Point): List<Point> {
            return buildList {
                for (column in min(point1.column, point2.column)..max(point1.column, point2.column)) {
                    for (row in min(point1.row, point2.row)..max(point1.row, point2.row)) {
                        this += Point(column, row)
                    }
                }
            }
        }

        fun parseLine(line: String): Set<Point> {
            return line.split(" -> ")
                .map { it.split(",") }
                .map { Point(it[0].toInt(), it[1].toInt()) }
                .windowed(2, 1)
                .flatMap { fillRocksBetweenPointsIncluding(it[0], it[1]) }
                .toSet()
        }
        return rocksRaw.flatMap { parseLine(it) }.toSet()
    }

    private fun isPossibleMove(targetPosition: Point, rocks: Set<Point>, sand: Set<Point>): Boolean {
        return targetPosition !in rocks && targetPosition !in sand
    }

    enum class Direction(val columnDelta: Int, val rowDelta: Int) {
        DOWN(0, 1),
        DOWN_LEFT(-1, 1),
        DOWN_RIGHT(1, 1)
    }

    data class Point(val column: Int, val row: Int) {
        fun move(direction: Direction): Point {
            return copy(column = column + direction.columnDelta, row = row + direction.rowDelta)
        }
    }

}