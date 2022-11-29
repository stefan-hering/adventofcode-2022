package adventofcode.day14

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day14/input")
    val testinput = readInput("/day14/testinput")
}

