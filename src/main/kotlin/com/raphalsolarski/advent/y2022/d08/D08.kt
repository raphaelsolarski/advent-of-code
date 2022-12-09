package com.raphalsolarski.advent.y2022.d08

object D08 {

    fun countVisibleTrees(map: List<String>): Int {
        val rows = map.size
        val columns = map.getOrNull(0)?.length ?: 0
        val visible = mutableSetOf<Pair<Int, Int>>()

        val possibleHeights = IntRange(0, 9)

        // ->
        for (row in IntRange(0, rows - 1)) {
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

    fun findHighestScenicScore(map: List<String>): Int {
        val housesStats = map.map { it.map { _ -> PossibleTreeHouse(null, null, null, null) } }
        val rows = map.size
        val columns = map.getOrNull(0)?.length ?: 0
        val visible = mutableSetOf<Pair<Int, Int>>()

        val possibleHeights = IntRange(0, 9)

        // ->
        for (row in IntRange(0, rows - 1)) {
            val lastIndexPerHighList = possibleHeights.toMutableList()
            lastIndexPerHighList.fill(-1)
            for (column in IntRange(0, columns - 1)) {
                val currentTreeHigh = map[row][column].toString().toInt()

                val firstHigherOrEqualTreeDistance =
                    lastIndexPerHighList.subList(currentTreeHigh, lastIndexPerHighList.size).filter { it != -1 }
                        .map { column - it }.minByOrNull { it }

                val distance = firstHigherOrEqualTreeDistance ?: (column)
                housesStats[row][column].distanceLeft = distance
                lastIndexPerHighList[currentTreeHigh] = column
            }
        }

        // <-
        for (row in IntRange(0, rows - 1)) {
            val lastIndexPerHighList = possibleHeights.toMutableList()
            lastIndexPerHighList.fill(-1)
            for (column in IntRange(0, columns - 1).reversed()) {
                val currentTreeHigh = map[row][column].toString().toInt()
                val firstHigherOrEqualTreeDistance =
                    lastIndexPerHighList.subList(currentTreeHigh, lastIndexPerHighList.size).filter { it != -1 }
                        .map { it - column }.minByOrNull { it }

                val distance = firstHigherOrEqualTreeDistance ?: (columns - column - 1)
                housesStats[row][column].distanceRight = distance
                lastIndexPerHighList[currentTreeHigh] = column
            }
        }

        // from up
        for (column in IntRange(0, columns - 1)) {
            val lastIndexPerHighList = possibleHeights.toMutableList()
            lastIndexPerHighList.fill(-1)
            for (row in IntRange(0, rows - 1)) {
                val currentTreeHigh = map[row][column].toString().toInt()
                val firstHigherOrEqualTreeDistance =
                    lastIndexPerHighList.subList(currentTreeHigh, lastIndexPerHighList.size).filter { it != -1 }
                        .map { row - it }.minByOrNull { it }

                val distance = firstHigherOrEqualTreeDistance ?: (row)
                housesStats[row][column].distanceUp = distance
                lastIndexPerHighList[currentTreeHigh] = row
            }
        }

        //from down
        for (column in IntRange(0, columns - 1)) {
            val lastIndexPerHighList = possibleHeights.toMutableList()
            lastIndexPerHighList.fill(-1)
            for (row in IntRange(0, rows - 1).reversed()) {
                val currentTreeHigh = map[row][column].toString().toInt()
                val firstHigherOrEqualTreeDistance =
                    lastIndexPerHighList.subList(currentTreeHigh, lastIndexPerHighList.size).filter { it != -1 }
                        .map { it - row }.minByOrNull { it }

                val distance = firstHigherOrEqualTreeDistance ?: (rows - row - 1)
                housesStats[row][column].distanceDown = distance
                lastIndexPerHighList[currentTreeHigh] = row
            }
        }


        val scores = housesStats.map { it.map { h -> h.score() } }
        println(scores[3][2])
        println(housesStats[3][2])
        return scores.flatten().max()
    }

    //todo: to immutable
    data class PossibleTreeHouse(
        var distanceUp: Int?,
        var distanceDown: Int?,
        var distanceRight: Int?,
        var distanceLeft: Int?
    ) {
        fun score(): Int {
            require(distanceUp != null && distanceDown != null && distanceLeft != null && distanceRight != null)
            return distanceUp!! * distanceDown!! * distanceRight!! * distanceLeft!!
        }
    }

}