package adventofcode.day18

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day18/input")
    val testinput = readInput("/day18/testinput")
}

