package com.raphalsolarski.advent.y2019.d04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class D4ImplTest {

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource(
            "111111 | true",
            "223450 | false",
            "123789 | false",
            delimiter = '|'
    )
    fun `part1-test-passwords-validity`(password: Int, expectedResult: Boolean) {
        assertEquals(expectedResult, D4Impl.testPassword(password))
    }

    @Test
    fun `part1-result`() {
        println(D4Impl.testRange(IntRange(D4Impl.part1InputMin, D4Impl.part1InputMax)))
    }

}