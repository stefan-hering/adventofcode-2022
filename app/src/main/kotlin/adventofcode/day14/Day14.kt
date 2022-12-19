package adventofcode.day14

fun readInput(file: String) = Unit.javaClass.getResource(file)
  .readText()
  .lines()
  .filter { it.isNotEmpty() }
  .map {
    it.split(" -> ")
      .map {
        it.split(",").let {
          it[1].toInt() to it[0].toInt()
        }
      }
  }

typealias Cave = Array<CharArray>
typealias Position = Pair<Int, Int>
typealias Path = List<Position>

val Position.x get() = first
val Position.y get() = second
operator fun Cave.get(position: Pair<Int, Int>): Char = this[position.x][position.y]

fun Cave.mark(from: Position, to: Position) {
  if (from.x == to.x) {
    val (yMin, yMax) = if (from.y < to.y) {
      from.y to to.y
    } else to.y to from.y

    for (i in yMin..yMax) {
      this[from.x][i] = '#'
    }
  } else {
    val (xMin, xMax) = if (from.x < to.x) {
      from.x to to.x
    } else to.x to from.x

    for (i in xMin..xMax) {
      this[i][from.y] = '#'
    }
  }
}

fun makeCave(paths: List<Path>): Array<CharArray> {
  val grid = Array(200) { CharArray(1000) { '.' } }

  paths.forEach { path ->
    path.zipWithNext()
      .forEach {
        grid.mark(it.first, it.second)
      }
  }

  return grid
}


fun dropSand(cave: Cave): Boolean {
  var position = 0 to 500
  var move = true

  fun next() {
    if(position.x + 1 == cave.size) {
      move = false
    } else {
      val down = position.copy(first = position.x + 1)
      if (cave[down] == '.') {
        position = down
        move = true
      } else {
        val downLeft = position.copy(first = position.x + 1, second = position.y - 1)
        if (cave[downLeft] == '.') {
          position = downLeft
          move = true
        } else {
          val downRight = position.copy(first = position.x + 1, second = position.y + 1)
          if (cave[downRight] == '.') {
            position = downRight
            move = true
          } else {
            move = false
          }
        }
      }
    }
  }

  while (move) {
    next()
  }

  return if(position != 0 to 500 && position.x + 1 < cave.size) {
    cave[position.x][position.y] = 'O'
    true
  } else false
}

fun part1(cave: Cave): Int {
  while (dropSand(cave)) {
  }

  return cave.sumOf {
    it.count { it == 'O' }
  }
}

fun part2(cave: Cave): Int {
  val bottom = cave.indexOfLast {
    it.any { it == '#' }
  } + 2

  cave.mark(bottom to 0, bottom to 999)
  while (dropSand(cave)) {
  }

  printCave(cave)

  return cave.sumOf {
    it.count { it == 'O' }
  } + 1
}

fun printCave(cave: Cave) =
  cave.forEach { println(it.joinToString(separator = "")) }


fun main() {
  val input = readInput("/day14/input")
  val testinput = readInput("/day14/testinput")

  println(part1(makeCave(input)))
  println(part2(makeCave(input)))
}

