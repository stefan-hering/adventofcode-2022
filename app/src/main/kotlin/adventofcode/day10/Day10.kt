package adventofcode.day10

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day10/input")
  val testinput = readInput("/day10/testinput")
}

