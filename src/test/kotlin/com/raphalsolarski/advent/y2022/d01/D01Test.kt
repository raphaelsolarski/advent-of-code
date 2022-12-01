package com.raphalsolarski.advent.y2022.d01

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class D01Test {

    val exampleInput = listOf(
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

    @Test
    fun exampleCase() {
        val result = D01.findMaxCaloriesCarryingElf(exampleInput)
        Assertions.assertEquals(D01.ElfInventory(3, 24000), result)
    }

    @Test
    fun firstStarTest() {
        val lines = ParseUtils.readLinesFromResource("/2022/01/input.txt", withEmptyLines = true)
        val result = D01.findMaxCaloriesCarryingElf(lines)
        Assertions.assertEquals(D01.ElfInventory(elfIndex = 10, elfCalories = 67622), result)
    }


    @Test
    fun secondStarExampleCase() {
        val result = D01.createElvesRanking(exampleInput).subList(0, 3).sumOf { it.elfCalories }
        Assertions.assertEquals(45000, result)
    }

    @Test
    fun secondStarTest() {
        val lines = ParseUtils.readLinesFromResource("/2022/01/input.txt", withEmptyLines = true)
        val result = D01.createElvesRanking(lines).subList(0, 3).sumOf { it.elfCalories }
        Assertions.assertEquals(201491, result)
    }

}