package com.raphalsolarski.advent.y2023.d03

object D03 {

    fun sumPartNumbers(exampleInput: List<String>): Int {
        val engineSchema = EngineSchema.parse(exampleInput)

        val allNumbers = engineSchema.allNumbers()
        return allNumbers.filter { it.hasAdjacentSymbol(engineSchema) }.sumOf { it.number }
    }

    data class EngineSchema(val rows: List<List<Char>>) {
        fun allNumbers(): List<SchemaNumber> {
            return rows.flatMapIndexed { rowIndex, row ->
                val digitsInRow = List(row.size) { columnIndex -> Point(columnIndex, rowIndex) }
                    .filter { element(it).isDigit() }

                divideNumbers(digitsInRow)
            }
        }

        private fun divideNumbers(digitsInRow: List<Point>): MutableList<SchemaNumber> {
            val numbers = mutableListOf<SchemaNumber>()
            val buffer = mutableListOf<Point>()
            digitsInRow.forEachIndexed { index, digitPoint ->
                buffer.add(digitPoint)
                if (digitsInRow.last() == digitPoint || digitsInRow[index + 1].column - 1 != digitPoint.column) {
                    numbers.add(SchemaNumber.fromPoints(buffer.toSet(), this))
                    buffer.clear()
                }
            }
            return numbers
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
            fun fromPoints(points: Set<Point>, schema: EngineSchema): SchemaNumber {
                val number = points.sortedBy { it.column }
                    .joinToString("") { schema.element(it).toString() }.toInt()
                return SchemaNumber(points.toSet(), number)
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
