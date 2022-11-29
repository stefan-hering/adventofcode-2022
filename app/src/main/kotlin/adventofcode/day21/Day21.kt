package adventofcode.day21

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day21/input")
    val testinput = readInput("/day21/testinput")
}

