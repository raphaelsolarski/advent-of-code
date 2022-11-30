package com.raphalsolarski.advent.utils

import java.lang.IllegalStateException

object ParseUtils {
    fun readLinesFromResource(resourcePath: String, withEmptyLines: Boolean = false): List<String> {
        val lines = javaClass.getResource(resourcePath)?.readText()?.split("\n")
        if (lines != null) {
            return lines.filter { it.isNotEmpty() || withEmptyLines }
        } else {
            throw IllegalStateException("Resource can't be read")
        }
    }
}