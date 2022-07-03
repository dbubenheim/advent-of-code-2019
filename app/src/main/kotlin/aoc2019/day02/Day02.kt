package aoc2019.day02

import aoc2019.day02.Addresses.NOUN
import aoc2019.day02.Addresses.VERB
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
        .apply { this[NOUN.value] = 12; this[VERB.value] = 2 }
    println("numbers: $numbers")

    val intCodes = numbers
        .windowed(size = 4, step = 4)
        .map { it.toIntCode() }
        .takeWhile { it.calc(numbers) }
    println("intCodes: $intCodes")

    return numbers.first()
}

fun part2(): Int {

    val numbers = "input-day02.txt".toFile()
        .readLines()
        .first()
        .split(",")
        .map { it.toInt() }
        .toMutableList()
    println("numbers: $numbers")

    (0..99).forEach { noun ->
        (0..99).forEach { verb ->
            val list = numbers.toMutableList()
            list.apply { this[NOUN.value] = noun; this[VERB.value] = verb }
                .windowed(size = 4, step = 4)
                .map { it.toIntCode() }
                .takeWhile { it.calc(list) }
            if (list.first() == 19690720) {
                println("list: $list")
                println("noun: $noun")
                println("verb: $verb")
                return 100 * noun + verb
            }
        }
    }
    return 0
}

fun main() {

    val part1 = part1()
    println("part1: $part1")

    val part2 = part2()
    println("part2: $part2")
}

fun List<Int>.toIntCode() = IntCode(opCode(), this[1], this[2], this[3])

private fun List<Int>.opCode() = OpcCode.fromInt(this[0])

data class IntCode(val opcode: OpcCode, val input1: Int, val input2: Int, val output: Int) {
    fun calc(numbers: MutableList<Int>): Boolean {
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

enum class OpcCode(val value: Int) {
    CODE_1(1),
    CODE_2(2),
    CODE_99(99);

    companion object {
        fun fromInt(value: Int) = OpcCode.values().firstOrNull { it.value == value }
            ?: throw IllegalArgumentException("Illegal OpCode: $value")
    }
}

enum class Addresses(val value: Int) {
    NOUN(1),
    VERB(2);

    companion object {
        fun fromInt(value: Int) = Addresses.values().firstOrNull { it.value == value }
            ?: throw IllegalArgumentException("Illegal Address: $value")
    }
}