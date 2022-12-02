package adventofcode.day02

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }
    .map { it.split(" ") }
    .map { it[0][0] to it[1][0] }

typealias Strategy = List<Pair<Char, Char>>

fun part1(strategy: Strategy) = strategy.sumOf {
  when (it.second) {
    'X' -> 1
    'Y' -> 2
    else -> 3
  } + when {
    it.first == it.second - 23 -> 3
    
    it.first == 'A' && it.second == 'Y' ||
        it.first == 'B' && it.second == 'Z' ||
        it.first == 'C' && it.second == 'X' -> 6

    else -> 0
  }
}

fun part2(strategy: Strategy) = strategy.sumOf {
  when (it.second) {
    'X' -> 0
    'Y' -> 3
    else -> 6
  } + when {
    it.first == 'A' && it.second == 'Y' ||
        it.first == 'B' && it.second == 'X' ||
        it.first == 'C' && it.second == 'Z' -> 1

    it.first == 'B' && it.second == 'Y' ||
        it.first == 'C' && it.second == 'X' ||
        it.first == 'A' && it.second == 'Z' -> 2

    else -> 3
  }
}


fun main() {
  val input = readInput("/day02/input")
  val testinput = readInput("/day02/testinput")

  println(part1(input))
  println(part2(input))
}

