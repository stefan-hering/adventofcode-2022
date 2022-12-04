package adventofcode.day03

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }
    .map { it.toCharArray() }

fun part1(rucksäcke: List<CharArray>): Int =
    rucksäcke.map {
      it.copyOfRange(0, it.size / 2) to it.copyOfRange(it.size / 2, it.size)
    }.mapNotNull { r ->
      r.first.find {
        r.second.contains(it)
      }
    }.map {
      priority(it)
    }.sum()

fun part2(rucksäcke: List<CharArray>) =
    rucksäcke.windowed(3, step = 3)
        .mapNotNull { elfGroup ->
          elfGroup[0].filter {
            elfGroup[1].contains(it)
          }.find {
            elfGroup[2].contains(it)
          }
        }
        .map { priority(it) }
        .sum()

fun priority(item: Char) =
    if (item.isUpperCase()) {
      item.toInt() - 38
    } else {
      item.toInt() - 96
    }

fun main() {
  val input = readInput("/day03/input")
  val testinput = readInput("/day03/input")

  println(part1(input))
  println(part2(testinput))
}

