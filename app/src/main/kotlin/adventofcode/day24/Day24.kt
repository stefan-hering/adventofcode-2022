package adventofcode.day24

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day24/input")
    val testinput = readInput("/day24/testinput")
}

