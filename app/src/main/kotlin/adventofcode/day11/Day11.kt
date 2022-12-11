package adventofcode.day11

import adventofcode.day11.Operator.PLUS
import adventofcode.day11.Operator.TIMES
import java.math.BigInteger

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .trim()
    .split("\n\n")
    .mapNotNull {
      "Monkey (\\d+):\\s+Starting items: ([\\d, ]+)\\s+Operation: new = ([\\w =*+]+)\\s+Test: divisible by (\\d+)\\s+If true: throw to monkey ([\\d]+)\\s+If false: throw to monkey ([\\d]+)"
          .toRegex()
          .matchEntire(it)
          ?.destructured
    }.map { (monkeyNumber, startingItems, operation, test, ifTrue, ifFalse) ->
      Monkey(
          monkeyNumber.toInt(),
          startingItems.split(", ").map { it.toBigInteger() }.toMutableList(),
          Operation(
              op = if (operation.contains("*")) TIMES else PLUS,
              other = operation.split(" ")[2].toBigIntegerOrNull()
          ),
          Test(
              test.toBigInteger()
          ),
          ifTrue.toInt(),
          ifFalse.toInt()
      )
    }

data class Monkey(
    val number: Int,
    val items: MutableList<BigInteger>,
    val operation: Operation,
    val test: Test,
    val targetIfTrue: Int,
    val targetIfFalse: Int,
    var inspections: Int = 0
)

enum class Operator { PLUS, TIMES }

data class Operation(
    val op: Operator,
    val other: BigInteger?
)

data class Test(
    val divisor: BigInteger
)

fun part1(monkeys: List<Monkey>, rounds: Int = 20, stopWorrying: Boolean = true): Long {
  val monkeyMultiplier = monkeys.fold(BigInteger.ONE) { acc,monkey ->
    monkey.test.divisor.multiply(acc)
  }

  (1..rounds).forEach {
    monkeys.forEach { monkey ->
      monkey.inspections += monkey.items.size

      val oldItems = listOf(*monkey.items.toTypedArray())
      monkey.items.clear()

      oldItems.forEach { item ->
        val other = if(monkey.operation.other == null) {
          item
        } else monkey.operation.other

        val newWorry = when(monkey.operation.op) {
          PLUS -> item.plus(other)
          TIMES -> item * other
        }.let {
          if (stopWorrying) {
            it.divide(3.toBigInteger())
          } else it.mod(monkeyMultiplier)
        }

        if(newWorry.mod(monkey.test.divisor).toInt() == 0) {
          monkeys[monkey.targetIfTrue].items.add(newWorry)
        } else {
          monkeys[monkey.targetIfFalse].items.add(newWorry)
        }
      }
      monkey.items.clear()
    }
  }

  val top2 = monkeys.map { it.inspections }
      .sortedDescending()
      .take(2)

  return top2[0].toLong() * top2[1]
}

fun main() {
  var input = readInput("/day11/input")
  val testinput = readInput("/day11/testinput")

  println(part1(input))

  input = readInput("/day11/input")
  println(part1(input, 10000, false))
}

