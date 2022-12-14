package com.raphalsolarski.advent.y2022.d12

object D12 {

    fun findClosestDistance(mapRaw: List<String>): Int {
        val map = parse(mapRaw)
        val startPoint = map.findPointWithChar('S')!!
        val endPoint = map.findPointWithChar('E')!!

        val discoveredPoints = mutableMapOf<Point, TrailPoint>()
        val toCheck = mutableSetOf<TrailPoint>()

        discoveredPoints[startPoint] = TrailPoint(startPoint, null)
        toCheck.add(TrailPoint(startPoint, null))

        while(toCheck.isNotEmpty()) {
            val pointToCheck = toCheck.elementAt(0)
//            println("point: ${pointToCheck.point} discovered: ${discoveredPoints.keys.size}")
            toCheck.remove(pointToCheck)
            for (possiblePoint in map.possiblePointsMoves(pointToCheck.point)) {
                if (possiblePoint in discoveredPoints) {
                    if (discoveredPoints[possiblePoint]!!.length() > pointToCheck.length() + 1) {
                        discoveredPoints[possiblePoint] = TrailPoint(possiblePoint, pointToCheck)
                        toCheck.add(TrailPoint(possiblePoint, pointToCheck))
                    }
                } else {
                    discoveredPoints[possiblePoint] = TrailPoint(possiblePoint, pointToCheck)
                    toCheck.add(TrailPoint(possiblePoint, pointToCheck))
                }
            }
        }

        return discoveredPoints[endPoint]!!.length()
    }

    private fun parse(map: List<String>): HilLMap {
        return HilLMap(map.map { it.toList() })
    }

    enum class Direction(val columnDelta: Int, val rowDelta: Int) {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0)
    }

    data class Point(val column: Int, val row: Int) {
        fun move(direction: Direction): Point {
            return copy(column = column + direction.columnDelta, row = row + direction.rowDelta)
        }
    }

    data class TrailPoint(val point: Point, val prevPoint: TrailPoint?) {
        fun length(): Int {
            return if (prevPoint == null) 0 else 1 + prevPoint.length()
        }
    }

    data class HilLMap(val internal: List<List<Char>>) {
        fun rows(): Int {
            return internal.size
        }

        fun columns(): Int {
            return internal[0].size
        }

        fun possiblePointsMoves(currentPoint: Point): List<Point> {
            return Direction.values().toList()
                .map { currentPoint.move(it) }
                .filter { inMapBounds(it) && availableByHigh(currentPoint, it)}
        }

        private fun inMapBounds(point: Point): Boolean {
            return (point.column in 0 until columns()) && (point.row in 0 until rows())
        }

        private fun availableByHigh(currentPoint: Point, targetPoint: Point): Boolean {
            val currentChar = pointChar(currentPoint)
            val targetChar = pointChar(targetPoint)
            return mapStartEndChars(currentChar).code + 1 >= mapStartEndChars(targetChar).code
        }

        private fun mapStartEndChars(char: Char): Char {
            return when (char) {
                'S' -> 'a'
                'E' -> 'z'
                else -> char
            }
        }

        private fun pointChar(point: Point): Char {
            return internal[point.row][point.column]
        }

        fun findPointWithChar(char: Char): Point? {
            for ((rowIndex, row) in internal.withIndex()) {
                for ((columnIndex, element) in row.withIndex()) {
                    if (element == char) {
                        return Point(columnIndex, rowIndex)
                    }
                }
            }
            return null
        }
    }

}