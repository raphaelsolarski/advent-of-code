package com.raphalsolarski.advent.y2022.d07

import com.raphalsolarski.advent.utils.ParseUtils
import org.junit.jupiter.api.Test

internal class D07Test {

    private val realInput = ParseUtils.readLinesFromResource("/2022/07/input.txt")

    private val exampleInput = listOf<String>(
        "$ cd /",
        "$ ls",
        "dir a",
        "14848514 b.txt",
        "8504156 c.dat",
        "dir d",
        "$ cd a",
        "$ ls",
        "dir e",
        "29116 f",
        "2557 g",
        "62596 h.lst",
        "$ cd e",
        "$ ls",
        "584 i",
        "$ cd ..",
        "$ cd ..",
        "$ cd d",
        "$ ls",
        "4060174 j",
        "8033020 d.log",
        "5626152 d.ext",
        "7214296 k",
    )

    private val diskSpace = 70000000
    private val desiredSpace = 30000000

    @Test
    fun star1examplesTest() {
        val allNodes = D07.createFilesystemFree(exampleInput).allNodes()
        val sum = allNodes.filter { it.treeSize() <= 100000 }.sumOf { it.treeSize() }
        println(sum)
    }

    @Test
    fun star1realTest() {
        val allNodes = D07.createFilesystemFree(realInput).allNodes()
        val sum = allNodes.filter { it.treeSize() <= 100000 }.sumOf { it.treeSize() }
        println(sum)
    }

    @Test
    fun star2examplesTest() {
        val root = D07.createFilesystemFree(realInput)
        val used = root.treeSize()
        println(used)
        val allNodes = root.allNodes()


        val nodes = allNodes.filter { diskSpace - used + it.treeSize() >= desiredSpace }.minBy { it.treeSize() }
        println(nodes.treeSize())
    }

    @Test
    fun star2realTest() {
//        assertEquals(3444, D06.findMessageMarker(realInput))
    }

}