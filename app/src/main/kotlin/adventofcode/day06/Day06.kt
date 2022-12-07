package adventofcode.day06

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .trim()

fun part1(signal: String, size: Int = 4) =
    signal.windowed(size = size)
      .indexOfFirst {
        it.chars().distinct().count() == size.toLong()
      } + size

fun main() {
  val input = readInput("/day06/input")
  val testinput = readInput("/day06/testinput")

  println(part1(input))
  println(part1(input, 14))
}

