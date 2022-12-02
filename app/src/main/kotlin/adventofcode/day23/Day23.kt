package adventofcode.day23

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day23/input")
  val testinput = readInput("/day23/testinput")
}

