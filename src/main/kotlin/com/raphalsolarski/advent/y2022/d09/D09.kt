package com.raphalsolarski.advent.y2022.d09

import com.raphalsolarski.advent.y2022.d09.D09.Direction.*
import kotlin.math.abs

object D09 {
    private val startingPoint = Point(0, 0)
    fun simulateAndCountVisitedByTail(movesRaw: List<String>, tailSize: Int): Int {
        require(tailSize >= 1)
        val moves = parseAndAtomizeMoves(movesRaw)
        val visitedByTail = mutableSetOf(startingPoint)
        var currentHeadPosition = startingPoint
        var knotsPositions = startingKnotsPositions(tailSize)
        for (headMove in moves) {
            val newHeadPosition = currentHeadPosition.move(headMove, 1)
            val newKnotsPositions = calcUpdatedSubsequentKnotsPositions(knotsPositions, newHeadPosition)
            visitedByTail.add(newKnotsPositions.last())
            currentHeadPosition = newHeadPosition
            knotsPositions = newKnotsPositions
        }
        return visitedByTail.size
    }

    private fun parseAndAtomizeMoves(movesRaw: List<String>): List<Direction> {
        return movesRaw.flatMap {
            val split = it.split(" ")
            IntRange(1, split[1].toInt()).map { Direction.parseDirectionSymbol(split[0][0]) }
        }
    }

    private fun startingKnotsPositions(knotsCount: Int): List<Point> {
        return IntRange(1, knotsCount).map { startingPoint }
    }

    private fun calcUpdatedSubsequentKnotsPositions(
        currentSubKnotsPositions: List<Point>,
        headPosition: Point
    ): List<Point> {
        return currentSubKnotsPositions.fold(listOf(headPosition)) { accumulatedUpdatedPositions, currentKnotPosition ->
            val updatedKnotPosition =
                calcUpdatedSubsequentKnotPosition(currentKnotPosition, accumulatedUpdatedPositions.last())
            accumulatedUpdatedPositions + updatedKnotPosition
        }.drop(1)
    }

    private fun calcUpdatedSubsequentKnotPosition(currentKnotPosition: Point, parentKnotPosition: Point): Point {
        if (currentKnotPosition.isAdjacent(parentKnotPosition))
            return currentKnotPosition
        val moveDirection = listOf(UP, DOWN, RIGHT, LEFT).find {
            currentKnotPosition.move(it, 2) == parentKnotPosition
        } ?: listOf(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT).first {
            currentKnotPosition.move(it, 1).isAdjacent(parentKnotPosition)
        }
        return currentKnotPosition.move(moveDirection, 1)
    }

    enum class Direction(val xDelta: Int, val yDelta: Int) {
        UP(0, 1),
        DOWN(0, -1),
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP_RIGHT(1, 1),
        UP_LEFT(-1, 1),
        DOWN_RIGHT(1, -1),
        DOWN_LEFT(-1, -1);

        companion object {
            private val headDirectionParseMap: Map<Char, Direction> = mapOf(
                'U' to UP,
                'D' to DOWN,
                'L' to LEFT,
                'R' to RIGHT
            )

            fun parseDirectionSymbol(symbol: Char): Direction {
                return headDirectionParseMap.getValue(symbol)
            }
        }
    }

    data class Point(val x: Int, val y: Int) {
        fun move(direction: Direction, distance: Int): Point {
            return copy(y = y + (direction.yDelta * distance), x = x + (direction.xDelta * distance))
        }

        fun isAdjacent(point: Point): Boolean {
            return (abs(x - point.x) <= 1 && abs(y - point.y) <= 1)
        }
    }
}