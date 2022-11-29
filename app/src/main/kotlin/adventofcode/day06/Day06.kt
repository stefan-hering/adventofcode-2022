package adventofcode.day06

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day06/input")
    val testinput = readInput("/day06/testinput")
}

