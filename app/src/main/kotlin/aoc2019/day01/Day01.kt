package aoc2019.day01

import aoc2019.toFile

fun rocketEquationPart1() = "input-day01.txt".toFile().readLines().sumOf { it.toInt().toFuelCount() }

fun rocketEquationPart2() = "input-day01.txt".toFile().readLines().sumOf { it.toInt().toFuelCount2() }

private fun Int.toFuelCount() = this / 3 - 2

private fun Int.toFuelCount2(): Int {
    var sum = 0
    var a = this
    while (a > 0) {
        a = a / 3 - 2
        if (a > 0) sum += a
    }
    return sum
}

fun main() {

    val fuelCount1969 = 1969.toFuelCount2()
    println(fuelCount1969)

    val fuelCount100756 = 100756.toFuelCount2()
    println(fuelCount100756)

    val rocketEquationPart1 = rocketEquationPart1()
    println("part1: $rocketEquationPart1")

    val rocketEquationPart2 = rocketEquationPart2()
    println("part2: $rocketEquationPart2")
}