package adventofcode.day13

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day13/input")
  val testinput = readInput("/day13/testinput")
}

