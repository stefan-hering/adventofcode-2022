package adventofcode.day09

import kotlin.math.abs
import kotlin.math.sign

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }
    .map { it.split(" ") }
    .map { it[0][0] to it[1].toInt() }

typealias Position = Pair<Int, Int>
typealias Rope = List<Position>
typealias Motion = Pair<Char, Int>

val Rope.head get() = first()
val Position.x get() = first
val Position.y get() = second
val Motion.direction get() = first
val Motion.length get() = second

operator fun Position.minus(other: Position): Position = x - other.x to y - other.y

operator fun Rope.plus(motion: Motion): List<Rope> {
  val (direction, length) = motion

  var ropeState: Rope = this
  val ropeStates = mutableListOf<Rope>()

  for (i in 1..length) {
    ropeState = ropeState.run {
      val newHead = when (direction) {
        'U' -> head.copy(first = head.x + 1)
        'R' -> head.copy(second = head.y + 1)
        'D' -> head.copy(first = head.x - 1)
        else -> head.copy(second = head.y - 1)
      }

      listOf(newHead) + this.drop(1).runningFold(newHead) { previous, knot ->
        val diff = previous - knot

        if (abs(diff.x) > 1 || abs(diff.y) > 1) {
          when {
            abs(diff.x) > 0 -> knot.x + diff.x.sign
            else -> knot.x
          } to when {
            abs(diff.y) > 0 -> knot.y + diff.y.sign
            else -> knot.y
          }
        } else knot
      }.drop(1)
    }

    ropeStates.add(ropeState)
  }
  return ropeStates
}

fun solve(motions: List<Motion>, ropeLength: Int) =
    motions.asSequence()
        .runningFold(
            listOf((1..ropeLength)
                .map { Position(0, 0) })
        ) { rope, motion ->
          rope.last() + motion
        }.flatten().map {
          it.last()
        }.distinct().count()


fun main() {
  val input = readInput("/day09/input")
  val testinput = readInput("/day09/testinput2")

  println(solve(input, 2))
  println(solve(input, 10))
}

