package adventofcode.day16

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day16/input")
  val testinput = readInput("/day16/testinput")
}

