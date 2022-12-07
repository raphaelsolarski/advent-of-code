package com.raphalsolarski.advent.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MatrixUtilsTest {

    @Test
    internal fun transposeFullMatrix() {
        val originalMatrix = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6)
        )

        val expectedMatrix = listOf(
            listOf(1, 4),
            listOf(2, 5),
            listOf(3, 6),
        )

        assertEquals(expectedMatrix, MatrixUtils.transposeMatrix(originalMatrix, 0))
    }

    @Test
    internal fun transposeWithPaddingMatrix() {
        val originalMatrix = listOf(
            listOf(1, 2),
            listOf(3, 4, 5),
            listOf(6, 7)
        )

        val expectedMatrix = listOf(
            listOf(1, 3, 6),
            listOf(2, 4, 7),
            listOf(0, 5, 0),
        )

        assertEquals(expectedMatrix, MatrixUtils.transposeMatrix(originalMatrix, 0))
    }

}