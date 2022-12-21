package adventofcode.day15

import kotlin.math.abs

fun readInput(file: String) = Unit.javaClass.getResource(file)
  .readText()
  .lines()
  .filter { it.isNotEmpty() }
  .mapNotNull {
    "Sensor at x=([-\\d]+), y=([-\\d]+): closest beacon is at x=([-\\d]+), y=([-\\d]+)".toRegex().matchEntire(it)?.destructured
  }.map { (sensorX, sensorY, beaconX, beaconY) ->
    Sensor(sensorX.toInt() to sensorY.toInt(), beaconX.toInt() to beaconY.toInt())
  }

typealias Position = Pair<Int, Int>

val Position.x get() = first
val Position.y get() = second

fun distance(a: Position, b: Position) = abs(a.x - b.x) + abs(a.y - b.y)

data class Sensor(
  val sensor: Position,
  val beacon: Position,
){
  val distance: Int = distance(sensor, beacon)
}

fun pointsWithoutBeacon(row: Int, sensors : List<Sensor>) =
  (-10_000_000..10_000_000).count { x ->
    sensors.any { sensor ->
      sensor.distance >= distance(sensor.sensor, x to row) &&
          sensor.beacon != x to row
    }
  }


fun pointWithBeacon(maxCoordinate: Int, sensors: List<Sensor>): Long {
  // This is just a bit smarter brute force, finishes in a few seconds, so good enough
  x@ for(x in 0 .. maxCoordinate) {
    var y = 0
    y@ while(y < maxCoordinate) {
      sensor@ for(sensor in sensors) {
        if(sensor.distance >= distance(sensor.sensor, x to y)) {
          y += (sensor.distance - distance(sensor.sensor, x to y)) + 1
          continue@y
        }
      }
      return 4000000L * x + y
    }
  }
  return -1
}

fun main() {
  val input = readInput("/day15/input")
  val testinput = readInput("/day15/testinput")

  pointsWithoutBeacon(10, testinput).also { println(it) }
  pointsWithoutBeacon(2000000, input).also { println(it) }

  pointWithBeacon(20, testinput).also { println(it) }
  pointWithBeacon(4000000, input).also { println(it) }
}

