package com.raphalsolarski.advent.y2022.d08

object D08 {

    fun countVisibleTrees(map: List<String>): Int {
        val rows = map.size
        val columns = map.getOrNull(0)?.length ?: 0
        val visible = mutableSetOf<Pair<Int, Int>>()

        val possibleHeights = IntRange(0, 9)

        // ->
        for (row in IntRange(0, rows - 1)) {
            val lastIndexPerHeightList = possibleHeights.toMutableList().fill(-1)
            var maxFromLeft = -1
            for (column in IntRange(0, columns - 1)) {
                val currentTreeHeight = map[row][column].toString().toInt()
                if (currentTreeHeight > maxFromLeft) {
                    visible.add(row to column)
                    maxFromLeft = currentTreeHeight
                }
            }
        }

        // <-
        for (row in IntRange(0, rows - 1)) {
            var maxFromRight = -1
            for (column in IntRange(0, columns - 1).reversed()) {
                val currentTreeHeight = map[row][column].toString().toInt()
                if (currentTreeHeight > maxFromRight) {
                    visible.add(row to column)
                    maxFromRight = currentTreeHeight
                }
            }
        }

        // from up
        for (column in IntRange(0, columns - 1)) {
            var maxFromUp = -1
            for (row in IntRange(0, rows - 1)) {
                val currentTreeHeight = map[row][column].toString().toInt()
                if (currentTreeHeight > maxFromUp) {
                    visible.add(row to column)
                    maxFromUp = currentTreeHeight
                }
            }
        }

        //from down
        for (column in IntRange(0, columns - 1)) {
            var maxFromDown = -1
            for (row in IntRange(0, rows - 1).reversed()) {
                val currentTreeHeight = map[row][column].toString().toInt()
                if (currentTreeHeight > maxFromDown) {
                    visible.add(row to column)
                    maxFromDown = currentTreeHeight
                }
            }
        }

        return visible.size
    }

    fun findHighestScenicScore(exampleInput: List<String>): Int {
        TODO("Not yet implemented")
    }

}