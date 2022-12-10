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

    private val high = 6

    private val wide = 40


    fun theMethod(commands: List<String>): Int {
        val parsedCommands = commands.map { parseCommand(it) }

        val xRegisterHistory = mutableListOf<Int>()

        var xRegisterValue = 1
        var cycles = 1

        for (command in parsedCommands) {
            if (command.name == "addx") {
                val arg = command.arg!!
                xRegisterHistory.add(xRegisterValue)
                xRegisterHistory.add(xRegisterValue)
                xRegisterValue += arg
                cycles += 2
            } else {
                xRegisterHistory.add(xRegisterValue)
                cycles++
            }
        }

        for(y in IntRange(0, high -1)) {
            for (x in IntRange(0, wide - 1)) {
                val currentCycle = (wide * y + x) + 1
                if (isSpriteInCurrentDrawPixel(currentCycle, xRegisterHistory)){
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }

        return 0
    }

    private fun isSpriteInCurrentDrawPixel(currentCycle: Int, registerHistory: List<Int>): Boolean {
        val xValueDuringCycle = registerHistory[currentCycle - 1]
        val spriteStartPosition = xValueDuringCycle - 1
        val spriteEndPosition = xValueDuringCycle + 1
        return (currentCycle % wide - 1) in spriteStartPosition..spriteEndPosition
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