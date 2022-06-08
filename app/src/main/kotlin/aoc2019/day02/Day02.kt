package aoc2019.day02

import aoc2019.day02.OpcCode.*
import aoc2019.toFile


/*
1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2).
2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6).
2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801).
1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99.
 */
fun part1(): Int {
    val numbers = "input-day02.txt".toFile()
        .readLines()
        .first()
        .split(",")
        .map { it.toInt() }
        .toMutableList()
        .apply { this[1] = 12; this[2] = 2 }
    val intCodes = numbers
        .windowed(size = 4, step = 4)
        .map { it.toIntcode() }
        .takeWhile { it.calc(numbers) }
    println("intCodes: $intCodes")
    println("numbers: $numbers")
    return numbers.first()
}

fun part2(): Long {
    return 0L
}

fun main() {

    val part1 = part1()
    println("part1: $part1")

    val part2 = part2()
    println("part2: $part2")
}

fun List<Int>.toIntcode() = IntCode(opCode(), this[1], this[2], this[3])

private fun List<Int>.opCode() = when (this[0]) {
    1 -> CODE_1
    2 -> CODE_2
    99 -> CODE_99
    else -> throw IllegalArgumentException("Illegal Opcode $this")
}

data class IntCode(val opcode: OpcCode, val input1: Int, val input2: Int, val output: Int) {
    fun calc(numbers: MutableList<Int>) : Boolean {
        return when (opcode) {
            CODE_1 -> {
                numbers[output] = numbers[input1] + numbers[input2]
                true
            }
            CODE_2 -> {
                numbers[output] = numbers[input1] * numbers[input2]
                true
            }
            CODE_99 -> false
        }
    }
}

enum class OpcCode { CODE_1, CODE_2, CODE_99 }