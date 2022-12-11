package com.raphalsolarski.advent.y2022.d11

import java.math.BigInteger

typealias UsedCompType = BigInteger

object D11 {
    private const val loggingEnabled = false

    fun stats(monkeysInput: List<Monkey>, rounds: Int, decreaser: UsedCompType = BigInteger.valueOf(3)): Long {
        val monkeys = simulate(monkeysInput, rounds, decreaser)
        val sortedByDescending = monkeys.sortedByDescending { it.inspectionsCount }
            .map { it.inspectionsCount }
//        println(sortedByDescending)
        return sortedByDescending.take(2)
            .reduce { m1, m2 -> m1 * m2 }
    }

    private fun simulate(monkeysInput: List<Monkey>, rounds: Int, decreaser: UsedCompType): List<Monkey> {
        val monkeys = monkeysInput.toList()
        for (round in IntRange(1, rounds)) {
            for (monkey in monkeys) {
                for (item in monkey.items) {
                    var worryLevel = item
                    worryLevel = monkey.operation(worryLevel)
//                    worryLevel = worryLevel / decreaser
                    if (monkey.test(worryLevel)) {
                        monkeys[monkey.ifTrueFollower].items.add(worryLevel)
                    } else {
                        monkeys[monkey.ifFalseFollower].items.add(worryLevel)
                    }
                    monkey.inspectionsCount = monkey.inspectionsCount + 1
                }
                monkey.items.clear()
            }
            if (round % 10 == 0) println(round)
            if (loggingEnabled) {
                if (round in setOf(1, 20, 100, 200, 1000)) {
                    println("== After round $round ==")
                    for ((index, monkey) in monkeys.withIndex()) {
                        println("Monkey $index inspected items ${monkey.inspectionsCount} times. current items: ${monkey.items}")
                    }
                    println()
                }
            }
        }
        return monkeys
    }

    data class Monkey(
        val items: MutableList<UsedCompType>,
        val operation: (UsedCompType) -> UsedCompType,
        val test: (UsedCompType) -> Boolean,
        val ifTrueFollower: Int,
        val ifFalseFollower: Int,
        var inspectionsCount: Long = 0
    )

}