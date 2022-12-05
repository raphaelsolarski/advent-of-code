package com.raphalsolarski.advent.y2022.d05

class D05 {
    companion object {
        fun topsAfterProcedure(stacks: List<List<Char>>, procedure: List<String>): List<Char?> {
            val afterProcedure = applyProcedure(stacks, procedure)
            return afterProcedure.map { it.getOrNull(0) }
        }

        fun topsAfterProcedureV2(stacks: List<List<Char>>, procedure: List<String>): List<Char?> {
            val afterProcedure = applyProcedureV2(stacks, procedure)
            return afterProcedure.map { it.getOrNull(0) }
        }

        fun applyProcedure(stacks: List<List<Char>>, procedure: List<String>): List<List<Char>> {
            val stacksMutable = stacks.map { it.toMutableList() }
            for (line in procedure) {
                val command = parseProcedureLine(line)

                for (amountIndex in IntRange(0, command.amount - 1)) {
                    val firstFrom = stacksMutable[command.from][0]
                    stacksMutable[command.from].removeAt(0)
                    stacksMutable[command.to].add(0, firstFrom)
                }
            }
            return stacksMutable.toList()
        }

        fun applyProcedureV2(stacks: List<List<Char>>, procedure: List<String>): List<List<Char>> {
            val stacksMutable = stacks.map { it.toMutableList() }
            for (line in procedure) {
                val command = parseProcedureLine(line)

                for (amountIndex in IntRange(0, command.amount - 1).reversed()) {
                    val firstFrom = stacksMutable[command.from][amountIndex]
                    stacksMutable[command.from].removeAt(amountIndex)
                    stacksMutable[command.to].add(0, firstFrom)
                }
            }
            return stacksMutable.toList()
        }

        fun parseProcedureLine(line: String): MoveCommand {
            "move 1 from 7 to 4".split(" ")
            val lineSplit = line.split(" ")
            val amount = lineSplit[1].toInt()
            val from = lineSplit[3].toInt() - 1
            val to = lineSplit[5].toInt() - 1
            return MoveCommand(from, to, amount)
        }

    }

    data class MoveCommand(val from: Int, val to: Int, val amount: Int)
}