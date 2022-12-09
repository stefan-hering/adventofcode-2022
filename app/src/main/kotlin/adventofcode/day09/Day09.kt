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

fun Int.sign() = if(this >= 0) 1 else -1

operator fun Rope.plus(motion: Motion): List<Rope> {
  val (direction, length) = motion

  var newRope: Rope = this
  val result = mutableListOf<Rope>()

  for (i in 1..length) {
    newRope = newRope.run {
      val newHead = when (direction) {
        'U' -> head.copy(first = head.x + 1)
        'R' -> head.copy(second = head.y + 1)
        'D' -> head.copy(first = head.x - 1)
        else -> head.copy(second = head.y - 1)
      }

      var prev = newHead
      val newTail = this.drop(1).map { tail ->
        val diff = prev - tail

        val newTail = when {
          abs(diff.x) == 2 -> tail.x + diff.x.sign to when {
            abs(diff.y) >= 1 -> tail.y + diff.y.sign
            else -> tail.y
          }

          abs(diff.y) == 2 -> when {
            abs(diff.x) >= 1 -> tail.x + diff.x.sign()
            else -> tail.x
          } to tail.y + diff.y.sign

          else -> tail
        }
        prev = newTail
        newTail
      }
      listOf(newHead) + newTail
    }
    result.add(newRope)
  }
  return result
}

fun solve(motions: List<Motion>, ropeLength: Int) =
    motions.runningFold(listOf((1..ropeLength).map { Position(0,0) })) { rope, motion ->
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

