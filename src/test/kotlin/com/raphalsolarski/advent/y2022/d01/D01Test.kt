package com.raphalsolarski.advent.y2022.d01

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class D01Test {

    private val exampleInput = listOf(
        "1000",
        "2000",
        "3000",
        "",
        "4000",
        "",
        "5000",
        "6000",
        "",
        "7000",
        "8000",
        "9000",
        "",
        "10000"
    )

    private val realInputLines = ParseUtils.readLinesFromResource("/2022/01/input.txt", withEmptyLines = true)

    @Test
    fun emptyInputTest() {
        Assertions.assertEquals(null, D01.findMaxCaloriesCarryingElf(listOf()))
    }

    @Test
    fun exampleCase() {
        val result = D01.findMaxCaloriesCarryingElf(exampleInput)
        Assertions.assertEquals(D01.ElfInventory(3, 24000), result)
    }

    @Test
    fun firstStarTest() {
        val result = D01.findMaxCaloriesCarryingElf(realInputLines)
        Assertions.assertEquals(D01.ElfInventory(elfIndex = 10, elfCalories = 67622), result)
    }

    @Test
    fun secondStarExampleCase() {
        val result = getSumOfFirst3(D01.createElvesRanking(exampleInput))
        Assertions.assertEquals(45000, result)
    }

    @Test
    fun secondStarTest() {
        val result = getSumOfFirst3(D01.createElvesRanking(realInputLines))
        Assertions.assertEquals(201491, result)
    }

    private fun getSumOfFirst3(ranking: List<D01.ElfInventory>) =
        ranking.subList(0, 3).sumOf { it.elfCalories }

}