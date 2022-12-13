package com.raphalsolarski.advent.y2022.d13

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.IntNode
import java.util.StringTokenizer

object D13 {

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

    private fun parsePacket(line: String): PacketDataList {
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


    interface PacketData

    data class PacketDataList(val list: List<PacketData>) : PacketData

    data class PacketDataInteger(val integer: Int) : PacketData
}