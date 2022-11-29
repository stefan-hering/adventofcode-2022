package adventofcode.day19

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .filter { it.isNotEmpty() }

fun main() {
    val input = readInput("/day19/input")
    val testinput = readInput("/day19/testinput")
}

