package adventofcode.day15

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day15/input")
  val testinput = readInput("/day15/testinput")
}

