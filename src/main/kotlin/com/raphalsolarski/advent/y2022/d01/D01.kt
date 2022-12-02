package com.raphalsolarski.advent.y2022.d01

class D01 {
    companion object {
        fun findMaxCaloriesCarryingElf(lines: List<String>): ElfInventory? {
            return createElvesRanking(lines).firstOrNull()
        }

        fun createElvesRanking(lines: List<String>): List<ElfInventory> {
            return chunkInputByEmptyLines(lines)
                .mapIndexed { index, chunk ->
                    ElfInventory(index, chunk.sumOf { it.toLong() })
                }
                .sortedByDescending { it.elfCalories }
        }

        private fun chunkInputByEmptyLines(input: List<String>): List<List<String>> {
            val chunks = mutableListOf<List<String>>()
            val currentChunk = mutableListOf<String>()
            for (line in input) {
                if (line != "") {
                    currentChunk.add(line)
                } else {
                    chunks.add(currentChunk.toList())
                    currentChunk.clear()
                }
            }
            if (currentChunk.isNotEmpty()) {
                chunks.add(currentChunk.toList())
            }
            return chunks
        }
    }

    data class ElfInventory(val elfIndex: Int, val elfCalories: Long)
}