package com.raphalsolarski.advent.y2022.d03

class D03 {

    companion object {
        fun computeGroupBadgesPriorities(lines: List<String>): Long {
            val rucksacks = lines.map(::parse)
            return divideIntoGroups(rucksacks)
                .map(::findDuplicateInGroup)
                .map(::computeItemScore)
                .sumOf { it }
        }

        private fun divideIntoGroups(lines: List<Rucksack>): List<Group> {
            return lines.chunked(3).map { Group(it) }
        }

        private fun findDuplicateInGroup(group: Group): Char {
            val allElementsPerRucksack = group.rucksacks.map { it.allElements.toSet() }.toList()
            val duplicatedElementList =
                allElementsPerRucksack[0].intersect(allElementsPerRucksack[1]).intersect(allElementsPerRucksack[2])
                    .toList()
            assert(duplicatedElementList.size == 1) { "Only one element can be duplicated" }
            return duplicatedElementList[0]
        }

        fun computeDuplicatedItemsScores(lines: List<String>): Long {
            return lines.map(::parse).flatMap(::findDuplicatedItems).map(::computeItemScore).sumOf { it }
        }

        private fun parse(line: String): Rucksack {
            val halfLength = line.length / 2
            return Rucksack(line.substring(0, halfLength).toList(), line.substring(halfLength).toList())
        }

        private fun findDuplicatedItems(rucksack: Rucksack): Set<Char> {
            return rucksack.firstCompartment.intersect(rucksack.secondCompartments.toSet())
        }

        fun computeItemScore(item: Char): Long {
            return if (item.isUpperCase()) {
                item.code.toLong() - 38
            } else {
                item.code.toLong() - 96
            }
        }

    }

    data class Rucksack(val firstCompartment: List<Char>, val secondCompartments: List<Char>) {
        val allElements: List<Char> = firstCompartment + secondCompartments
    }

    data class Group(val rucksacks: List<Rucksack>)

}