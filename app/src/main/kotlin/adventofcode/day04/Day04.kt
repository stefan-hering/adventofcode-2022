package adventofcode.day04

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }
    .map {
      it.split(",")
          .map {
            it.split("-").map { it.toInt() }
          }
    }
    .map {
      it[0][0]..it[0][1] to it[1][0]..it[1][1]
    }

typealias SectionAssignment = Pair<IntRange, IntRange>

fun part1(assignments: List<SectionAssignment>) =
    assignments.count { (elf1, elf2) ->
      elf1.first >= elf2.first && elf1.last <= elf2.last ||
          elf1.first <= elf2.first && elf1.last >= elf2.last
    }

fun part2(assignments: List<SectionAssignment>) =
    assignments.count { (elf1, elf2) ->
      elf1.last >= elf2.first && elf1.first <= elf2.last ||
          elf1.last <= elf2.first && elf1.first >= elf2.last
    }

fun main() {
  val input = readInput("/day04/input")
  val testinput = readInput("/day04/testinput")

  println(part1(input))
  println(part2(input))
}

