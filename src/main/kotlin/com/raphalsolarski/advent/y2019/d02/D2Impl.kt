package com.raphalsolarski.advent.y2019.d02

import java.lang.IllegalStateException

class D2Impl {
    companion object {
        fun compute(input: List<Int>): List<Int> {
            val program = input.toMutableList()
            var instructionPointer = 0;
            while (true) {
                val instruction = program[instructionPointer]
                if (instruction == 1) {
                    val param1 = program[program[instructionPointer + 1]]
                    val param2 = program[program[instructionPointer + 2]]
                    val target = program[instructionPointer + 3]
                    program[target] = param1 + param2
                } else if (instruction == 2) {
                    val param1 = program[program[instructionPointer + 1]]
                    val param2 = program[program[instructionPointer + 2]]
                    val target = program[instructionPointer + 3]
                    program[target] = param1 * param2
                } else if (instruction == 99) {
                    break
                } else {
                    throw IllegalStateException("Unknown instruction: $instruction")
                }
                instructionPointer += 4
            }
            return program
        }

    }
}