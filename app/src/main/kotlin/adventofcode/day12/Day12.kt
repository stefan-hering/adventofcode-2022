package adventofcode.day12

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day12/input")
  val testinput = readInput("/day12/testinput")
}

