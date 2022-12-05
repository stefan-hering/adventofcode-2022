package adventofcode.day05

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .split("\n\n")
    .let { parseStacks(it[0].lines()) to parseMoves(it[1].lines()) }

data class Move(
    val count: Int,
    val from: Int,
    val to: Int
)

typealias Stacks = List<ArrayDeque<Char>>

fun parseStacks(lines: List<String>): Stacks {
  val stackCount = (lines.maxOf { it.length } + 1) / 4

  val stacks = (0 until stackCount).map {
    ArrayDeque<Char>()
  }

  lines.filter { it.isNotBlank() }
      .forEach {
        var stack = 0
        for(i in 1 .. it.length step 4) {
          if (it[i - 1] == '[') {
            stacks[stack].addFirst(it[i])
          }
          stack++
        }
      }

  return stacks
}

fun parseMoves(lines: List<String>): List<Move> =
    lines.filter { it.isNotBlank() }
        .map {
          it.split(" ")
        }.map {
          Move(
              count = it[1].toInt(),
              from = it[3].toInt() - 1,
              to = it[5].toInt() - 1
          )
        }


fun rearrange(stacks: Stacks, moves: List<Move>, multiple: Boolean = false): String {
  moves.forEach { move ->
    val movedElements = stacks[move.from].removeStack(move.count, multiple)
    stacks[move.to].addAll(movedElements)
  }

  return stacks.mapNotNull {
    if (it.isNotEmpty()) {
      it.last()
    } else null
  }.joinToString(separator = "")
}

fun <T> ArrayDeque<T>.removeStack(n: Int, multiple: Boolean): List<T> =
    (0 until n).map {
      removeLast()
    }.let { if(multiple) it.reversed() else it }

fun main() {
  val input = readInput("/day05/input")
  val testinput = readInput("/day05/testinput")

  println(rearrange(input.first, input.second))

  val input2 = readInput("/day05/input")
  println(rearrange(input2.first, input2.second, true))
}

