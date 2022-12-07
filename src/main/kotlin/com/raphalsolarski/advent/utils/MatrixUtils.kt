package com.raphalsolarski.advent.utils

object MatrixUtils {

    fun <E> transposeMatrix(matrix: List<List<E>>, paddingElement: E): List<List<E>> {
        val maxWidth = matrix.maxOf { it.size }
        val transposedMatrix = mutableListOf<List<E>>()
        for (targetRowIndex in IntRange(0, maxWidth - 1)) {
            val targetRow = mutableListOf<E>()
            for (targetColumnIndex in IntRange(0, matrix.size - 1)) {
                targetRow.add(
                    getOrPaddingElement(
                        matrix,
                        originalRow = targetColumnIndex,
                        originalColumn = targetRowIndex,
                        paddingElement = paddingElement
                    )
                )
            }
            transposedMatrix.add(targetRow)
        }
        return transposedMatrix
    }

    private fun <E> getOrPaddingElement(
        originalMatrix: List<List<E>>,
        originalRow: Int,
        originalColumn: Int,
        paddingElement: E
    ): E {
        return if (originalMatrix.size <= originalRow || originalMatrix[originalRow].size <= originalColumn) {
            paddingElement
        } else {
            originalMatrix[originalRow][originalColumn]
        }
    }

}