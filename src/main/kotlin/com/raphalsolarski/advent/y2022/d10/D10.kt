package com.raphalsolarski.advent.y2022.d10

object D10 {
    private val interestingCycles = listOf(
        20,
        60,
        100,
        140,
        180,
        220
    )
    fun theMethod(commands: List<String>): Int {
        val parsedCommands = commands.map { parseCommand(it) }

        val strengthHistory = mutableListOf<Int>()

        var xRegisterValue = 1
        var cycles = 1

        for (command in parsedCommands) {
            if (command.name == "addx") {
                val arg = command.arg!!
                strengthHistory.add(signalStrength(cycles, xRegisterValue))
                strengthHistory.add(signalStrength(cycles + 1, xRegisterValue))
                xRegisterValue += arg
                cycles += 2
            } else {
                strengthHistory.add(signalStrength(cycles, xRegisterValue))
                cycles++
            }
        }

        return interestingCycles.map { strengthHistory[it - 1] }.sum()
    }

    private fun parseCommand(commandRaw: String): Command {
        if (commandRaw == "noop") {
            return Command("noop", null)
        }
        val split = commandRaw.split(" ")
        return Command(split[0], split[1].toInt())
    }

    private fun signalStrength(cycle: Int, xRegisterValue: Int): Int {
        return cycle * xRegisterValue
    }

    fun theMethod2(lines: List<String>): Long {
        TODO()
    }

    data class Command(val name: String, var arg: Int?)
}