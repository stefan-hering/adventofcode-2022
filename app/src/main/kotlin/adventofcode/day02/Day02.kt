package adventofcode.day02

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }
    

fun main() {
  val input = readInput("/day02/input")
  val testinput = readInput("/day02/testinput")
}

