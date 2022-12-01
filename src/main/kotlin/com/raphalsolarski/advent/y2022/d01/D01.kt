package com.raphalsolarski.advent.y2022.d01

class D01 {
    companion object {
        fun findMaxCaloriesCarryingElf(lines: List<String>): ElfInventory {
            var currentMax: Long = 0
            var currentMaxIndex = 0
            var currentElfIndex = 0
            val elves = mutableListOf<ElfInventory>()
            elves.add(ElfInventory(0, 0))
            for (line in lines) {
                if (line == "") {
                    elves.add(ElfInventory(currentElfIndex + 1, 0))
                    currentElfIndex++;
                    continue
                }
                val currentElfOriginal = elves[currentElfIndex]
                val elfUpdated = currentElfOriginal.copy(elfCalories = currentElfOriginal.elfCalories + line.toLong())
                elves[currentElfIndex] = elfUpdated
                if (currentMax < elfUpdated.elfCalories) {
                    currentMaxIndex = elfUpdated.elfIndex
                    currentMax = elfUpdated.elfCalories
                }
            }
            return elves[currentMaxIndex]
        }

        fun createElvesRanking(lines: List<String>): List<ElfInventory> {
            var currentMax: Long = 0
            var currentMaxIndex = 0
            var currentElfIndex = 0
            val elves = mutableListOf<ElfInventory>()
            elves.add(ElfInventory(0, 0))
            for (line in lines) {
                if (line == "") {
                    elves.add(ElfInventory(currentElfIndex + 1, 0))
                    currentElfIndex++;
                    continue
                }
                val currentElfOriginal = elves[currentElfIndex]
                val elfUpdated = currentElfOriginal.copy(elfCalories = currentElfOriginal.elfCalories + line.toLong())
                elves[currentElfIndex] = elfUpdated
                if (currentMax < elfUpdated.elfCalories) {
                    currentMaxIndex = elfUpdated.elfIndex
                    currentMax = elfUpdated.elfCalories
                }
            }
            return elves.sortedByDescending { it.elfCalories }
        }
    }

    data class ElfInventory(val elfIndex: Int, val elfCalories: Long)
}