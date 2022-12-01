package adventofcode.day01

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .split("\n\n")
    .map {
      it.lines().map { it.toInt() }
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

