package com.raphalsolarski.advent.y2022.d13

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.IntNode

object D13 {
    fun sumRightPairsIndices(lines: List<String>): Int {
        return parse(lines).withIndex()
            .filter { isInRightOrder(it.value.first, it.value.second)!! }
            .sumOf { it.index + 1 }
    }

    fun parse(lines: List<String>): List<Pair<PacketDataList, PacketDataList>> {
        return chunkByEmptyLines(lines).map {
            parsePacket(it.first) to parsePacket(it.second)
        }
    }

    private fun chunkByEmptyLines(lines: List<String>): List<Pair<String, String>> {
        val emptyLinesIndices = lines.withIndex().filter { it.value == "" }.map { it.index }
        return emptyLinesIndices.map { index ->
            lines[index - 2] to lines[index - 1]
        }
    }

    fun parsePacket(line: String): PacketDataList {
        val objectMapper = ObjectMapper()
        val tree = objectMapper.readTree(line)
        return mapJsonNode(tree) as PacketDataList
    }

    private fun mapJsonNode(node: JsonNode): PacketData {
        return when (node) {
            is IntNode -> PacketDataInteger(node.asInt())
            is ArrayNode -> PacketDataList(node.elements().asSequence().map { mapJsonNode(it!!) }.toList())
            else -> throw IllegalStateException("Unhandled json node type: ${node.nodeType}")
        }
    }

    fun isInRightOrder(packetDataLeft: PacketDataList, packetDataRight: PacketDataList): Boolean? {
        //lists
        for (pair in packetDataLeft.list.zip(packetDataRight.list)) {
            val (left, right) = pair
            if (left is PacketDataList && right is PacketDataList) {
                val inRightOrder = isInRightOrder(left, right)
                if (inRightOrder != null)
                    return inRightOrder
            } else if (left is PacketDataList && right is PacketDataInteger) {
                val inRightOrder = isInRightOrder(left, PacketDataList(listOf(right)))
                if (inRightOrder != null)
                    return inRightOrder
            } else if (left is PacketDataInteger && right is PacketDataList) {
                val inRightOrder = isInRightOrder(PacketDataList(listOf(left)), right)
                if (inRightOrder != null)
                    return inRightOrder
            } else if (left is PacketDataInteger && right is PacketDataInteger) {
                if (left.integer < right.integer)
                    return true
                else if (left.integer > right.integer)
                    return false
            }
        }
        return if (packetDataRight.list.size < packetDataLeft.list.size)
            false
        else if (packetDataRight.list.size > packetDataLeft.list.size)
            true
        else
            null
    }


    interface PacketData

    data class PacketDataList(val list: List<PacketData>) : PacketData

    data class PacketDataInteger(val integer: Int) : PacketData
}