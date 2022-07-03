package aoc2019.day04

import aoc2019.toFile

fun part1() = "input-day04.txt".toFile()
    .readLines()
    .first()
    .split("-")
    .map { it.toInt() }
    .run {
        (this.first()..this.last())
            .count { it.toString().isValid() }
    }

fun part2(): Int {
    return 0
}

fun main() {

    val part1 = part1()
    println("part1: $part1")

    val part2 = part2()
    println("part2: $part2")
}


/*
It is a six-digit number.
The value is within the range given in your puzzle input.
Two adjacent digits are the same (like 22 in 122345).
Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
*/
fun String.isValid() = this.length == 6 && this.hasAdjacent() && this.isIncreasing()
fun String.hasAdjacent() = this.windowed(2).any { it.first() == it.last() }
fun String.isIncreasing() = this.windowed(2).none { it.first() > it.last() }