package com.raphalsolarski.advent.y2022.d07

object D07 {
    fun createFilesystemFree(commands: List<String>): TreeNode {
        val treeRoot = TreeNode("/", mutableListOf(), mutableListOf(), null)
        var currentNode = treeRoot
        for ((index, command) in commands.withIndex()) {
            if (command.startsWith("$")) {
                if (command.startsWith("$ cd")) {
                    val path = command.substring(5)
                    if (path == "/") {
                        currentNode = treeRoot
                    } else if(path == "..") {
                        currentNode = currentNode.parent!!
                    } else {
                        val searchedNode = currentNode.nodes.find { it.name == path }
                        if (searchedNode != null) {
                            currentNode = searchedNode
                        } else {
                            val newNode = TreeNode(path, mutableListOf(), mutableListOf(), currentNode)
                            currentNode.nodes.add(newNode)
                            currentNode = newNode
                        }
                    }
                } else if (command == "$ ls") {
                    for (output in commands.subList(index + 1, commands.size)) {
                        if (!output.startsWith("$")) {
                            val split = output.split(" ")
                            if (split[0] == "dir") {
                                // nothing
                            } else {
                                currentNode.leafs.add(TreeLeaf(split[1], split[0].toLong(), currentNode))
                            }
                        } else {
                            break
                        }
                    }
                } else {
                    throw IllegalStateException("Unknown command $command")
                }
            }
        }
        return treeRoot
    }

    data class TreeLeaf(val name: String, val size: Long, val parent: TreeNode)
    data class TreeNode(val name: String, val nodes: MutableList<TreeNode>, val leafs: MutableList<TreeLeaf>, val parent: TreeNode?) {
        fun treeSize(): Long {
            return nodes.sumOf { it.treeSize() } + leafs.sumOf { it.size }
        }

        fun allNodes(): List<TreeNode> = listOf(this) + nodes.flatMap { it.allNodes() }
    }

}