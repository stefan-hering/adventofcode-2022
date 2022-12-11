package adventofcode.day10

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }
    .map {
      if (it == "noop") {
        Noop()
      } else {
        Addx(it.split(" ")[1].toInt())
      }
    }

sealed interface Operation

class Noop : Operation
data class Addx(
    val x: Int
) : Operation

data class ProgramState(
    val cycle: Int = 0,
    val x: Int = 1,
    private val addX: Int = 0,
) {
  fun noop() = next()

  fun addx(other: Int) =
      next().let {
        listOf(it, it.copy(cycle = it.cycle + 1, addX = other))
      }

  private fun next(): ProgramState =
      ProgramState(cycle = cycle + 1, x = x + addX, addX = 0)
}

fun calculateStates(ops: List<Operation>): List<ProgramState> =
    ops.fold(listOf(ProgramState())) { state, op ->
      when (op) {
        is Noop -> state + state.last().noop()
        is Addx -> state + state.last().addx(op.x)
      }
    }

fun part1(ops: List<Operation>): Int =
    calculateStates(ops).filter {
      (it.cycle + 20) % 40 == 0 && it.cycle <= 220
    }.fold(0) { acc, state ->
      acc + (state.x * state.cycle)
    }

fun part2(ops: List<Operation>): List<String> =
    calculateStates(ops).drop(1).map { state ->
      if (state.cycle % 40 >= state.x && state.cycle % 40 <= state.x + 2) {
        '#'
      } else '.'
    }.windowed(40, 40).map {
      it.joinToString(separator = "")
    }

fun main() {
  val input = readInput("/day10/input")
  val testinput = readInput("/day10/testinput")

  println(part1(input))
  part2(input).forEach(::println)
}

