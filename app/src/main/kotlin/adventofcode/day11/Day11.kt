package adventofcode.day11

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day11/input")
    val testinput = readInput("/day11/testinput")
}

