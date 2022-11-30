package com.raphalsolarski.advent.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ParseUtilsTest {

    @Test
    fun parseTest() {
        val linesWithoutEmptyLines = ParseUtils.readLinesFromResource("/parse-test.txt")
        Assertions.assertEquals(listOf("a b c", "a b c d", "foo"), linesWithoutEmptyLines)

        val linesWithEmptyLines = ParseUtils.readLinesFromResource("/parse-test.txt", withEmptyLines = true)
        Assertions.assertEquals(listOf("a b c", "a b c d", "", "foo", ""), linesWithEmptyLines)
    }

}