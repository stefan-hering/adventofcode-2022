package adventofcode.day22

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

fun main() {
  val input = readInput("/day22/input")
  val testinput = readInput("/day22/testinput")
}

