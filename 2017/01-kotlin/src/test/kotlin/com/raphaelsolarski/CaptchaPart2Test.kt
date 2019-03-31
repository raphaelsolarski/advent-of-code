package com.raphaelsolarski

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class CaptchaPart2Test {

    @Test
    internal fun test1() {
        val result = Captcha.computeHalfway(listOf(1, 2, 1, 2))
        Assertions.assertEquals(6, result)
    }

    @Test
    internal fun test2() {
        val result = Captcha.computeHalfway(listOf(1, 2, 2, 1))
        Assertions.assertEquals(0, result)
    }

    @Test
    internal fun test3() {
        val result = Captcha.computeHalfway(listOf(1, 2, 3, 4, 2, 5))
        Assertions.assertEquals(4, result)
    }

    @Test
    internal fun test4() {
        val result = Captcha.computeHalfway(listOf(1, 2, 3, 1, 2, 3))
        Assertions.assertEquals(12, result)
    }

    @Test
    internal fun test5() {
        val result = Captcha.computeHalfway(listOf(1, 2, 1, 3, 1, 4, 1, 5))
        Assertions.assertEquals(4, result)
    }

    @Test
    internal fun part2() {
        val numbers = parseInput()
        val result = Captcha.computeHalfway(numbers)
        println(result)
    }

    private fun parseInput() = Files.readAllLines(Paths.get("input.txt"))[0].map { Integer.parseInt(it.toString()) }
}