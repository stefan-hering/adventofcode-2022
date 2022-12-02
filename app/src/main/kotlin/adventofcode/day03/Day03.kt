package adventofcode.day03

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day03/input")
  val testinput = readInput("/day03/testinput")
}

