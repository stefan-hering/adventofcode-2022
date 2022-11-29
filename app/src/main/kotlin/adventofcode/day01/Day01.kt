package adventofcode.day01

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day01/input")
    val testinput = readInput("/day01/testinput")
}

