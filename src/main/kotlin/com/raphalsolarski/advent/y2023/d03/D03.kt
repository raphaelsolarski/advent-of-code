package com.raphalsolarski.advent.y2023.d03

object D03 {

    fun sumPartNumbers(exampleInput: List<String>): Int {
        val engineSchema = EngineSchema.parse(exampleInput)

        val allNumbers = engineSchema.allNumbers()
        return allNumbers.filter { it.hasAdjacentSymbol(engineSchema) }.sumOf { it.number }
    }

    data class EngineSchema(val rows: List<List<Char>>) {
        // todo: refactor
        fun allNumbers(): List<SchemaNumber> {
            return rows.flatMapIndexed { rowIndex, row ->
                val digitsInRow = row.flatMapIndexed { columnIndex, element ->
                    if (element.isDigit()) {
                        listOf(Point(columnIndex, rowIndex) to element.digitToInt())
                    } else {
                        listOf()
                    }
                }
                val foldResult = digitsInRow
                    .fold(listOf<SchemaNumber>() to listOf<Pair<Point, Int>>()) { (detectedNumbers, numberInPrepare), digit ->
                        if (numberInPrepare.isEmpty() || numberInPrepare.last().first.column + 1 == digit.first.column) {
                            detectedNumbers to numberInPrepare.plus(digit)
                        } else {
                            detectedNumbers.plus(SchemaNumber.fromPoints(numberInPrepare)) to listOf(digit)
                        }
                    }
                if (foldResult.second.isEmpty()) foldResult.first else foldResult.first.plus(
                    SchemaNumber.fromPoints(
                        foldResult.second
                    )
                )
            }
        }

        fun element(point: Point): Char {
            return if (point.row < 0 || point.column < 0
                || point.row >= rows.size || point.column >= rows[0].size
            ) {
                '.'
            } else {
                rows[point.row][point.column]
            }
        }

        companion object {
            fun parse(exampleInput: List<String>): EngineSchema {
                return EngineSchema(exampleInput.map { it.toList() })
            }
        }
    }


    data class SchemaNumber(val points: Set<Point>, val number: Int) {

        fun hasAdjacentSymbol(schema: EngineSchema): Boolean {
            return points.any { pointOfNumber ->
                pointOfNumber.adjacentPoints().any { adjPoint ->
                    val element = schema.element(adjPoint)
                    (element != '.' && !element.isDigit())
                }
            }
        }

        companion object {
            fun fromPoints(points: Collection<Pair<Point, Int>>): SchemaNumber {
                val number = points.sortedBy { it.first.column }.map { it.second.toString() }.joinToString("").toInt()
                return SchemaNumber(points.map { it.first }.toSet(), number)
            }
        }
    }

    data class Point(val column: Int, val row: Int) {
        fun adjacentPoints(): Set<Point> {
            return setOf(
                Point(column - 1, row - 1),
                Point(column, row - 1),
                Point(column + 1, row - 1),

                Point(column - 1, row),
                Point(column + 1, row),

                Point(column - 1, row + 1),
                Point(column, row + 1),
                Point(column + 1, row + 1),
            )
        }
    }
}
