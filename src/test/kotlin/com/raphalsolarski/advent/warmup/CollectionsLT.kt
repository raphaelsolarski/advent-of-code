package com.raphalsolarski.advent.warmup

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class CollectionsLT {

    @Test
    fun listAccessOperator() {
        val list1 = listOf(1, 2, 3)
        assertEquals(3, list1[2])
    }

    @Test
    fun mappingList() {
        val list1 = listOf(1, 2, 3).map { elem -> elem + 1 }
        assertEquals(list1, listOf(2, 3, 4))
    }

    @Test
    fun filteringList() {
        assertEquals(
            listOf(2),
            listOf(1, 2, 3).filter { it % 2 == 0 }
        )
    }

    @Test
    fun foldingList() {
        assertEquals(
            6,
            listOf(1, 2, 3).fold(0) { acc, elem -> acc + elem }
        )
    }

    @Test
    fun flatmapList() {
        assertEquals(listOf(listOf(1), listOf(2)).flatten(), listOf(1, 2))
        assertEquals(listOf(listOf(1, 2), listOf(2)).flatMap { e -> e }, listOf(1, 2, 2))
    }

    @Test
    fun flatmapListOfOptionals() {
        assertEquals(
            listOf(Optional.of(1), Optional.empty()).flatMap { e -> e.map { listOf(it) }.orElse(emptyList()) },
            listOf(1)
        )
    }

    @Test
    internal fun pairs() {
        val pair = 1 to "foo"
        val (key, value) = pair
        assertEquals(1, key)
        assertEquals("foo", value)
    }

    @Test
    internal fun triple() {
        val (first, second, third) = Triple(1, 2, 3)
        assertEquals(1, first)
        assertEquals(2, second)
        assertEquals(3, third)
    }

    @Test
    fun maps() {
        val immutableMap = mapOf(1 to "foo", 2 to "bar")
        assertEquals("foo", immutableMap[1]) // immutable map doesn't support appending with create like in scala

        val mutableMap = mutableMapOf(1 to "foo", 2 to "bar")
        mutableMap[3] = "bar2"
        assertEquals(mapOf(1 to "foo", 2 to "bar", 3 to "bar2"), mutableMap)

        assertTrue(3 in mutableMap)
    }

    @Test
    fun foldingMap() {
        assertEquals(
            3,
            mapOf(1 to "a", 2 to "b").toList().fold(0) { acc, e -> acc + e.first }
        )
    }

    @Test
    internal fun creatingMapWithBuilder() {
        assertEquals(
            mapOf(1 to "foo", 2 to "bar"),
            buildMap {
                put(1, "foo")
                put(2, "bar")
                put(3, "bar3")
                remove(3)
            }
        )

        // using access operator
        assertEquals(
            mapOf(1 to "foo", 2 to "bar"),
            buildMap {
                this[1] = "foo"
                this[2] = "bar"
            }
        )
    }

    @Test
    fun sets() {
        val set = mutableSetOf(1, 2, 3)
        assertTrue(set.contains(1))
        assertFalse(set.contains(4))

        assertTrue(1 in set)
        assertFalse(4 in set)

        assertEquals(listOf(1, 2, 3), set.toList())

        set.add(4)
        assertEquals(set, setOf(1, 2, 3, 4))
    }

    @Test
    internal fun nullability() {
        data class Foo(val name: String, val points: Int)
        Foo("John", 5)

        data class Bar(val name: String, val mail: String?)
        val mail1: String = Bar("John", "foo@example.com").mail ?: "foo"
        val mail2: String = Bar("Anna", null).mail ?: "foo"

        assertEquals("foo@example.com", mail1)
        assertEquals("foo", mail2)
    }
}