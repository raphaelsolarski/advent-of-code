package com.raphalsolarski.advent.y2022.d16

object D16 {
    private val connectedValvesRegexSplit = Regex("(valves)|(valve)")

    fun parseLine(lineRaw: String): Valve {
        val name = lineRaw.split(" has")[0].substring(6)
        val flowRate = lineRaw.split("rate=")[1].substringBefore(";").toInt()
        val connectedValves = lineRaw.split(connectedValvesRegexSplit)[1].trim().split(", ").toList()
        return Valve(name, flowRate, connectedValves)
    }

    data class Valve(val name: String, val flowRate: Int, val connectedValves: List<String>)
}