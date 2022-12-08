package adventofcode.day08

import kotlin.math.max

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }
    .map { it.toCharArray().map { it.digitToInt() }.toIntArray() }.toTypedArray()

fun part1(treeGrid: Array<IntArray>): Long {
  val visible = Array(treeGrid.size) { BooleanArray(treeGrid[0].size) }

  for (i in treeGrid.indices) {
    val row = treeGrid[i]

    listOf(row.indices, row.indices.reversed()).forEach {
      var max = -1
      for (j in it) {
        val tree = treeGrid[i][j]
        if (tree > max) {
          visible[i][j] = true
          max = tree
        }
      }
    }
  }

  for (j in treeGrid[0].indices) {
    listOf(treeGrid.indices, treeGrid.indices.reversed()).forEach {
      var max = -1
      for (i in it) {
        val tree = treeGrid[i][j]
        if (tree > max) {
          visible[i][j] = true
          max = tree
        }
      }
    }
  }

  return visible.sumOf { row -> row.sumOf { if (it) 1L else 0 } }
}

fun part2(treeGrid: Array<IntArray>): Int {
  var maxScore = 0

  for (x in 1 until treeGrid.size - 1) {
    for (y in 1 until treeGrid[0].size - 1) {
      val tree = treeGrid[x][y]

      var left = 1
      while(y - left > 0 && tree > treeGrid[x][y - left]) {
        left++
      }

      var right = 1
      while(y + right < treeGrid[0].size - 1 && tree > treeGrid[x][y + right]) {
        right++
      }

      var up = 1
      while(x - up > 0 && tree > treeGrid[x - up][y]) {
        up++
      }

      var down = 1
      while(x + down < treeGrid.size - 1 && tree > treeGrid[x + down][y]) {
        down++
      }


      val score = left * right * up * down
      if(score > maxScore) {
        maxScore = score
      }
    }
  }

  return maxScore
}

fun main() {
  val input = readInput("/day08/input")
  val testinput = readInput("/day08/testinput")

  println(part1(input))
  println(part2(input))
}

