package adventofcode.day08

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day08/input")
  val testinput = readInput("/day08/testinput")
}

