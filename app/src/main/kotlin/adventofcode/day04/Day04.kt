package adventofcode.day04

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day04/input")
    val testinput = readInput("/day04/testinput")
}

