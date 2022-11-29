package adventofcode.day05

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day05/input")
    val testinput = readInput("/day05/testinput")
}

