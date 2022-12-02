package adventofcode.day09

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day09/input")
  val testinput = readInput("/day09/testinput")
}

