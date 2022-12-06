package com.raphalsolarski.advent.y2022.d06

class D06 {
    companion object {
        fun findStartMarker(line: String): Int {
            return findFirstDistinctSequence(line, 4)
        }

        fun findMessageMarker(line: String): Int {
            return findFirstDistinctSequence(line, 14)
        }

        private fun findFirstDistinctSequence(line: String, sequenceLength: Int): Int {
            val buffer = mutableListOf<Char>()
            for (charWithIndex in line.withIndex()) {
                if (buffer.size == sequenceLength) {
                    buffer.removeAt(0)
                }
                buffer.add(charWithIndex.value)
                if (buffer.distinct().size == sequenceLength) {
                    return charWithIndex.index + 1
                }
            }
            return -1
        }
    }
}