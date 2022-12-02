package adventofcode.day20

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day20/input")
  val testinput = readInput("/day20/testinput")
}

