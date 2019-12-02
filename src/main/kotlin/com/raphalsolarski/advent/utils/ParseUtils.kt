package com.raphalsolarski.advent.utils

import java.lang.IllegalStateException

object ParseUtils {
    fun readLines(resourcePath: String): List<String> {
        val lines = javaClass.getResource(resourcePath)?.readText()?.split("\n")
        if (lines != null) {
            return lines.filter { it.isNotEmpty() }
        } else {
            throw IllegalStateException("File can't be read")
        }
    }
}