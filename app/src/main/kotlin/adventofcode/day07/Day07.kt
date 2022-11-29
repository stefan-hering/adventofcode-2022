package adventofcode.day07

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day07/input")
    val testinput = readInput("/day07/testinput")
}

