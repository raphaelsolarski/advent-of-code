package com.raphaelsolarski

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class CaptchaPart1Test {

    @Test
    internal fun test1() {
        val result = Captcha.computeComparingToNext(listOf(1, 1, 2, 2))
        assertEquals(3, result)
    }

    @Test
    internal fun test2() {
        val result = Captcha.computeComparingToNext(listOf(1, 1, 1, 1))
        assertEquals(4, result)
    }

    @Test
    internal fun test3() {
        val result = Captcha.computeComparingToNext(listOf(1, 2, 3, 4))
        assertEquals(0, result)
    }

    @Test
    internal fun test4() {
        val result = Captcha.computeComparingToNext(listOf(9, 1, 2, 1, 2, 1, 2, 9))
        assertEquals(9, result)
    }

    @Test
    internal fun part1() {
        val numbers = Files.readAllLines(Paths.get("input.txt"))[0].map { Integer.parseInt(it.toString()) }
        val result = Captcha.computeComparingToNext(numbers)
        println(result)
    }
}