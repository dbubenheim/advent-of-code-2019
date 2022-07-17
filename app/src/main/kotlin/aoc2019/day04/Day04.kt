package aoc2019.day04

import aoc2019.toFile

fun part1() = "input-day04.txt".toFile()
    .readLines()
    .first()
    .split("-")
    .map { it.toInt() }
    .run {
        (this.first()..this.last())
            .count { it.toString().isValidPart1() }
    }

fun part2() = "input-day04.txt".toFile()
    .readLines()
    .first()
    .split("-")
    .map { it.toInt() }
    .run {
        (this.first()..this.last())
            .count { it.toString().isValidPart2() }
    }

fun main() {

    val part1 = part1()
    println("part1: $part1")

    val part2 = part2()
    println("part2: $part2")

    println("112233".hasAdjacentPart2())
    println("123444".hasAdjacentPart2())
    println("111122".hasAdjacentPart2())
}


/*
It is a six-digit number.
The value is within the range given in your puzzle input.
Two adjacent digits are the same (like 22 in 122345).
Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
*/
fun String.isValidPart1() = this.length == 6 && this.hasAdjacentPart1() && this.isIncreasing()
fun String.isValidPart2() = this.length == 6 && this.hasAdjacentPart2() && this.isIncreasing()
fun String.hasAdjacentPart1() = this.windowed(2).any { it.first() == it.last() }

/*
112233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.
123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).
111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).
 */
fun String.hasAdjacentPart2() = this.windowed(2)
    .filter { it[0] == it[1] }
    .groupingBy { it }
    .eachCount()
    .any { it.value == 1 }

fun String.isIncreasing() = this.windowed(2).none { it.first() > it.last() }