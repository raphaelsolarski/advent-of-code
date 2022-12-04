package com.raphalsolarski.advent.y2022.d04

class D04 {
    companion object {
        fun countPairThatFullyOverlap(lines: List<String>): Int {
            return lines.map { parse(it) }.count { testFullyOverlap(it) }
        }

        fun countPairThatOverlap(lines: List<String>): Int {
            return lines.map { parse(it) }.count { testOverlap(it) }
        }

        private fun parse(line: String): Pair<LongRange, LongRange> {
            val split = line.split(",")
            return parseAssigment(split[0]) to parseAssigment(split[1])
        }

        private fun parseAssigment(assigment: String): LongRange {
            val assigmentSplit = assigment.split("-")
            return LongRange(assigmentSplit[0].toLong(), assigmentSplit[1].toLong())
        }

        private fun testFullyOverlap(assigmentPair: Pair<LongRange, LongRange>): Boolean {
            val (assigment1, assigment2) = assigmentPair
            return ((assigment1.first <= assigment2.first && assigment1.last >= assigment2.last) ||
                    (assigment2.first <= assigment1.first && assigment2.last >= assigment1.last))
        }

        private fun testOverlap(assigmentPair: Pair<LongRange, LongRange>): Boolean {
            val (assigment1, assigment2) = assigmentPair
            return assigment1.intersect(assigment2).isNotEmpty()
        }
    }
}