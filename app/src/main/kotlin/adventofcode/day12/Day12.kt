package adventofcode.day12

import kotlin.system.measureTimeMillis

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }
    .map {
      it.toCharArray().map {
        when (it) {
          'S' -> 0
          'E' -> 27
          else -> it - 'a' + 1
        }
      }.toIntArray()
    }
    .toTypedArray()

typealias Hill = Array<IntArray>
typealias Position = Pair<Int, Int>

val Position.x get() = first
val Position.y get() = second

fun Hill.startEnd(): Pair<Position, Position> {
  var start: Position = -1 to -1
  var end: Position = -1 to -1

  for (i in indices) {
    for (j in this[i].indices) {
      if (this[i][j] == 0) {
        start = i to j
      }
      if (this[i][j] == 27) {
        end = i to j
      }
    }
  }
  return start to end
}

operator fun Hill.get(pos: Position) = this[pos.x][pos.y]

fun part1(hill: Hill): Int {
  val (start, end) = hill.startEnd()

  val distances = calculateDistances(hill, start)

  return distances[end.x][end.y]
}


fun part2(hill: Hill): Int {
  val (_, end) = hill.startEnd()

  // this could be much more efficient if searching from the end, but this is still 2 digit ms
  val starts = mutableListOf<Position>()
  hill.mapIndexed { x, row ->
    row.mapIndexed { y, height ->
      if (height == 1) {
        starts.add(x to y)
      }
    }
  }

  var minDistance = Int.MAX_VALUE
  starts.forEach { start ->
    val distances = calculateDistances(hill, start)
    if (distances[end.x][end.y] > 0) {
      minDistance = minOf(distances[end.x][end.y], minDistance)
    }
  }

  return minDistance
}

private fun calculateDistances(hill: Hill, start: Position): Array<IntArray> {
  val distances = Array(hill.size) { IntArray(hill[0].size) { -1 } }
  distances[start.x][start.y] = 0

  val nodes = ArrayDeque<Position>()
  nodes.add(start)

  val walk = { current: Position, next: Position ->
    if (distances[next] == -1 && hill[next] - hill[current] < 2) {
      distances[next.x][next.y] = distances[current] + 1
      nodes.addLast(next)
    }
  }

  while (nodes.isNotEmpty()) {
    val current = nodes.removeFirst()

    if (current.x > 0) {
      walk(current, current.copy(first = current.x - 1))
    }
    if (current.x < hill.size - 1) {
      walk(current, current.copy(first = current.x + 1))
    }

    if (current.y > 0) {
      walk(current, current.copy(second = current.y - 1))
    }
    if (current.y < hill[0].size - 1) {
      walk(current, current.copy(second = current.y + 1))
    }
  }
  return distances
}


fun main() {
  val input = readInput("/day12/input")
  val testinput = readInput("/day12/testinput")

  println(part1(input))
  println(part2(input))
}

