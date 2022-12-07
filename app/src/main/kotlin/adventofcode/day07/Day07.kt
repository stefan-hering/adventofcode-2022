package adventofcode.day07

fun readInput(file: String) = Unit.javaClass.getResource(file)
    .readText()
    .lines()
    .filter { it.isNotEmpty() }

data class Directory(
    val name: String,
    var size: Int = 0,
    val subdirectories: MutableList<Directory> = mutableListOf(),
    var parent: Directory? = null
)

fun parseDirectories(input: List<String>): Directory {
  var currentDirectory: Directory = Directory("/")
  val root = currentDirectory

  input.drop(1)
      .map { it.split(" ") }
      .forEach { line ->
        when {
          line[0] == "$" && line[1] == "cd" ->
            when {
              line[2] == ".." -> currentDirectory = currentDirectory.parent!!
              currentDirectory.subdirectories.any {
                it.name == line[2]
              } -> currentDirectory = currentDirectory.subdirectories.find { it.name == line[2] }!!

              else -> currentDirectory = Directory(line[2], parent = currentDirectory).also {
                currentDirectory.subdirectories.add(it)
              }
            }

          line[0] != "$" && line[0] != "dir" -> {
            currentDirectory.size += line[0].toInt()
            var parent = currentDirectory.parent
            while (parent != null) {
              parent.size += line[0].toInt()
              parent = parent.parent
            }
          }
        }
      }

  return root
}

fun part1(dir: Directory): Int =
    dir.subdirectories.sumOf {
      part1(it)
    } + if (dir.size <= 100000) dir.size else 0

fun part2(root: Directory): Int {
  val minToDelete = root.size - (70000000 - 30000000)

  return findMin(root, minToDelete)
}

fun findMin(dir: Directory, minToDelete: Int): Int =
    minOf(
        dir.subdirectories.minOfOrNull {
          findMin(it, minToDelete)
        } ?: Int.MAX_VALUE,
        if (dir.size < minToDelete)
          Int.MAX_VALUE
        else
          dir.size
    )

fun main() {
  val input = readInput("/day07/input")
  val testinput = readInput("/day07/testinput")

  val root = parseDirectories(input)

  println(part1(root))
  println(part2(root))
}

