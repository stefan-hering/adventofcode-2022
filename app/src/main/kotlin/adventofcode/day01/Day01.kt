package adventofcode.day01

fun readInput(file: String) = Unit.javaClass.getResource(file)
        .readText()
        .lines()
        .fold(mutableListOf(mutableListOf<Int>())) { acc, line ->
            if (line.isBlank()) {
                acc.add(mutableListOf())
            } else {
                acc.last().add(line.toInt())
            }
            acc
        }

typealias Elf = List<Int>

fun part1(elves: List<Elf>) = elves
        .map {
            it.sum()
        }.max()

fun part2(elves: List<Elf>) = elves
        .map {
            it.sum()
        }.sortedDescending()
        .take(3)
        .sum()

fun main() {
    val input = readInput("/day01/input")
    val testinput = readInput("/day01/testinput")

    println(part1(input))
    println(part2(input))
}

