package adventofcode.day25

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day25/input")
    val testinput = readInput("/day25/testinput")
}

