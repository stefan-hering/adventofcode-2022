package adventofcode.day08

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }
    .map { it.toCharArray().map { it.digitToInt() }.toIntArray() }.toTypedArray()

fun Array<BooleanArray>.markVisibleTree(x: Int, y: Int, tree: Int, max: Int): Int {
  if (tree > max) {
    this[x][y] = true
    return tree
  }
  return max
}

fun part1(forest: Array<IntArray>): Long {
  val visibleTrees = Array(forest.size) { BooleanArray(forest[0].size) }

  listOf(forest.indices, forest.indices.reversed()).forEach { columnIndices ->
    listOf(forest[0].indices, forest[0].indices.reversed()).forEach { rowIndices ->
      for (y in rowIndices) {
        var max = -1
        for (x in columnIndices) {
          max = visibleTrees.markVisibleTree(x, y, forest[x][y], max)
        }
      }
      for (x in columnIndices) {
        var max = -1
        for (y in rowIndices) {
          max = visibleTrees.markVisibleTree(x, y, forest[x][y], max)
        }
      }
    }
  }

  return visibleTrees.sumOf { row -> row.sumOf { if (it) 1L else 0 } }
}

fun part2(treeGrid: Array<IntArray>): Int {
  var maxScore = 0

  for (x in 1 until treeGrid.size - 1) {
    for (y in 1 until treeGrid[0].size - 1) {
      val tree = treeGrid[x][y]

      var left = 1
      while (y - left > 0 && tree > treeGrid[x][y - left]) {
        left++
      }

      var right = 1
      while (y + right < treeGrid[0].size - 1 && tree > treeGrid[x][y + right]) {
        right++
      }

      var up = 1
      while (x - up > 0 && tree > treeGrid[x - up][y]) {
        up++
      }

      var down = 1
      while (x + down < treeGrid.size - 1 && tree > treeGrid[x + down][y]) {
        down++
      }


      val score = left * right * up * down
      if (score > maxScore) {
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

