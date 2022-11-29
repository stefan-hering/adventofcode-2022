package adventofcode.day17

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day17/input")
    val testinput = readInput("/day17/testinput")
}

