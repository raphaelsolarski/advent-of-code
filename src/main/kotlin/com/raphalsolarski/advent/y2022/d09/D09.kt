package com.raphalsolarski.advent.y2022.d09

import com.raphalsolarski.advent.y2022.d09.D09.HeadMoveDirection.*
import kotlin.math.abs

object D09 {
    private val startingPoint = Point(0, 0)

    private val headDirectionParseMap: Map<Char, HeadMoveDirection> = mapOf(
        'U' to UP,
        'D' to DOWN,
        'L' to LEFT,
        'R' to RIGHT
    )

    fun simulateAndCountVisitedByTail(movesRaw: List<String>, tailSize: Int): Int {
        var ticks = 0
        require(tailSize >= 1)
        val moves = parseAndAtomizeMoves(movesRaw)
        val visitedByTail = mutableSetOf(startingPoint)
        var headPosition = startingPoint
        var knotsPositions = startingKnotsPositions(tailSize)
        for (headMove in moves) {
            val newHeadPosition = calcNewHeadPosition(headPosition, headMove)
            val newKnotsPositions = calcNewKnotsPositions(knotsPositions, newHeadPosition, headPosition)
            val newTailPosition = newKnotsPositions.last()
            visitedByTail.add(newTailPosition)
            headPosition = newHeadPosition
            knotsPositions = newKnotsPositions
            ticks++
        }

        return visitedByTail.size
    }

    private fun calcNewHeadPosition(currentPosition: Point, direction: HeadMoveDirection): Point {
        return currentPosition.move(direction, 1)
    }

    private fun calcNewKnotsPositions(
        currentKnotsPositions: List<Point>,
        newHeadPosition: Point,
        prevHeadPosition: Point
    ): List<Point> {
        return currentKnotsPositions.fold(((newHeadPosition to prevHeadPosition) to listOf<Point>())) { parentPositions, currentKnotPosition ->
            val newKnotPosition =
                calcNewKnotPosition(currentKnotPosition, parentPositions.first.first, parentPositions.first.second)
            (newKnotPosition to currentKnotPosition) to parentPositions.second + newKnotPosition
        }.second
    }

    private fun calcNewKnotPosition(
        currentKnotPosition: Point,
        newParentKnotPosition: Point,
        prevParentKnotPosition: Point
    ): Point {
        if (currentKnotPosition.isAdjacent(newParentKnotPosition))
            return currentKnotPosition
        if (currentKnotPosition.move(UP, 2) == newParentKnotPosition)
            return currentKnotPosition.move(UP, 1)
        if (currentKnotPosition.move(DOWN, 2) == newParentKnotPosition)
            return currentKnotPosition.move(DOWN, 1)
        if (currentKnotPosition.move(RIGHT, 2) == newParentKnotPosition)
            return currentKnotPosition.move(RIGHT, 1)
        if (currentKnotPosition.move(LEFT, 2) == newParentKnotPosition)
            return currentKnotPosition.move(LEFT, 1)
        val moveDirection = listOf(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT).find {
            currentKnotPosition.move(it, 1).isAdjacent(newParentKnotPosition)
        }!!
        return currentKnotPosition.move(moveDirection, 1)
    }

    private fun parseAndAtomizeMoves(movesRaw: List<String>): List<HeadMoveDirection> {
        return movesRaw.flatMap {
            val split = it.split(" ")
            IntRange(1, split[1].toInt()).map { headDirectionParseMap.getValue(split[0][0]) }
        }
    }

    private fun startingKnotsPositions(knotsCount: Int): List<Point> {
        return IntRange(1, knotsCount).map { startingPoint }
    }

    enum class HeadMoveDirection(val xDelta: Int, val yDelta: Int) {
        UP(0, 1), DOWN(0, -1), LEFT(-1, 0), RIGHT(1, 0),
        UP_RIGHT(1, 1), UP_LEFT(-1, 1), DOWN_RIGHT(1, -1), DOWN_LEFT(-1, -1)
    }

    data class Point(val x: Int, val y: Int) {
        fun move(direction: HeadMoveDirection, distance: Int): Point {
            return copy(y = y + (direction.yDelta * distance), x = x + (direction.xDelta * distance))
        }

        fun isAdjacent(point: Point): Boolean {
            return (abs(x - point.x) <= 1 && abs(y - point.y) <= 1)
        }
    }
}