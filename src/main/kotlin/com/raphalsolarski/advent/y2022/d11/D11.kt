package com.raphalsolarski.advent.y2022.d11

object D11 {
    //not sure if it would work for non-primary numbers
    private const val loggingEnabled = false

    fun stats(monkeysInput: List<Monkey>, rounds: Int, decreaser: Long = 3): Long {
        val monkeys = simulate(monkeysInput, rounds, decreaser)
        val sortedByDescending = monkeys.sortedByDescending { it.inspectionsCount }
            .map { it.inspectionsCount }
//        println(sortedByDescending)
        return sortedByDescending.take(2)
            .reduce { m1, m2 -> m1 * m2 }
    }

    private fun simulate(monkeysInput: List<Monkey>, rounds: Int, decreaser: Long): List<Monkey> {
        val monkeys = InternalMonkey.fromInputMonkeys(monkeysInput)
        for (round in IntRange(1, rounds)) {
            for ((monkeyIndex, monkey) in monkeys.withIndex()) {
                for (item in monkey.items) {
                    val itemAfterOperation = item.applyOperation(monkey.inputMonkey.operation)
                    if (itemAfterOperation.test(monkeyIndex)) {
                        monkeys[monkey.inputMonkey.ifTrueFollower].items.add(itemAfterOperation)
                    } else {
                        monkeys[monkey.inputMonkey.ifFalseFollower].items.add(itemAfterOperation)
                    }
                    monkey.inputMonkey.inspectionsCount++
                }
                monkey.items.clear()
            }
            if (loggingEnabled) {
                if (round in setOf(1, 20, 100, 200, 1000)) {
                    println("== After round $round ==")
                    for ((index, monkey) in monkeys.withIndex()) {
                        println("Monkey $index inspected items ${monkey.inputMonkey.inspectionsCount} times.")
                    }
                    println()
                }
            }
        }
        return monkeysInput
    }

    data class Monkey(
        val startItems: MutableList<Long>,
        val operation: (Long) -> Long,
        val testDivisor: Long,
        val ifTrueFollower: Int,
        val ifFalseFollower: Int,
        var inspectionsCount: Long = 0
    )

    data class Item(val remindersForMonkeyTestDividers: List<Long>, val monkeyDividers: List<Long>) {

        fun applyOperation(operation: (Long) -> Long): Item {
            return copy(
                remindersForMonkeyTestDividers = remindersForMonkeyTestDividers.zip(monkeyDividers).map { pair ->
                    val (currentReminder, monkeyDivider) = pair
                    operation(currentReminder) % monkeyDivider
                })
        }

        fun test(monkeyId: Int): Boolean {
            return remindersForMonkeyTestDividers[monkeyId] % monkeyDividers[monkeyId] == 0L
        }

        companion object {
            fun create(dividers: List<Long>, value: Long): Item {
                return Item(dividers.map { value % it }, dividers)
            }
        }
    }

    data class InternalMonkey(
        val items: MutableList<Item>,
        val inputMonkey: Monkey
    ) {
        companion object {
            fun fromInputMonkeys(inputMonkeys: List<Monkey>): List<InternalMonkey> {
                val dividers = inputMonkeys.map { it.testDivisor }
                return inputMonkeys.map {
                    InternalMonkey(it.startItems.map { item -> Item.create(dividers, item) }.toMutableList(), it)
                }
            }
        }
    }

}