package adventofcode.day13

fun readInput(file: String) = Unit.javaClass.getResource(file)
  .readText()
  .split("\n\n")
  .map { it.lines() }
  .map { parse(it[0]) to parse(it[1]) }

sealed class Packet

data class Integer(val value: Int) : Packet()
data class Lists(val value: MutableList<Packet> = mutableListOf()) : Packet()

enum class Result {
  YES, NO, MAYBE
}

fun parse(input: String): Lists {
  val stack = ArrayDeque<Lists>()

  var currentNum = ""
  val pushCurrentNum = {
    if (currentNum != "") {
      stack.last().value.add(Integer(currentNum.toInt()))
      currentNum = ""
    }
  }

  var root = Lists()
  var i = 0
  while (i < input.length) {
    when (input[i]) {
      '[' -> {
        stack.addLast(Lists().also {
          if (stack.isNotEmpty()) {
            stack.last().value.add(it)
          }
        })
      }

      ']' -> {
        pushCurrentNum()
        root = stack.removeLast()
      }

      ',' -> pushCurrentNum()
      else -> currentNum += input[i]
    }

    i++
  }

  return root
}

fun part1(input: List<Pair<Packet, Packet>>) =
  input.mapIndexedNotNull { index, pair ->
    val result = (rightOrder(pair.first, pair.second))
    if (result != Result.NO) {
      index
    } else null
  }.map { it + 1 }.sum()

fun rightOrder(left: Packet, right: Packet): Result {
  return when {
    left is Integer && right is Integer -> when {
      left.value < right.value -> Result.YES
      left.value == right.value -> Result.MAYBE
      else -> Result.NO
    }

    left is Lists && right is Lists -> {
      var result = Result.MAYBE
      for (i in left.value.indices) {
        if (result != Result.MAYBE) {
          break
        }

        result = if (right.value.size <= i) {
          Result.NO
        } else {
          rightOrder(left.value[i], right.value[i])
        }
      }
      if(result == Result.MAYBE) {
        Result.YES
      } else result
    }

    else -> {
      if (left is Integer) {
        rightOrder(Lists(mutableListOf(left)), right)
      } else {
        rightOrder(left, Lists(mutableListOf(right)))
      }
    }
  }
}

fun part2(input: List<Pair<Packet, Packet>>): Int {
  val div1 = Lists(mutableListOf(Lists(mutableListOf(Integer(2)))))
  val div2 = Lists(mutableListOf(Lists(mutableListOf(Integer(6)))))

  val sorted = (input + (div1 to div2))
    .flatMap { listOf(it.first, it.second) }
    .sortedWith { a,b ->
      if(rightOrder(a,b) == Result.YES) {
        -1
      } else 1
    }

  return (sorted.indexOf(div1) + 1) * (sorted.indexOf(div2) + 1)
}


fun main() {
  val input = readInput("/day13/input")
  val testinput = readInput("/day13/testinput")


  part1(testinput)
  println(part1(input))

  println(part2(input))
}

