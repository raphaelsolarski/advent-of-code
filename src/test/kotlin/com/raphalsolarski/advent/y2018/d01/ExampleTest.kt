package com.raphalsolarski.advent.y2018.d01

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ExampleTest {
    
    @Test
    fun `some fun test`() {
        assertTrue(true)
    }
    
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource(
            "0,    1,   1",
            "1,    2,   3",
            "49,  51, 100",
            "1,  100, 101"
    )
    fun `some parametrized test`(first: Int, second: Int, expectedResult: Int) {
        assertEquals(expectedResult, first + second)
    }
    
}