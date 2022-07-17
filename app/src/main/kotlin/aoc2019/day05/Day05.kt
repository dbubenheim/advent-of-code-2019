package aoc2019.day05

import aoc2019.day05.OpCode.*
import aoc2019.day05.ParameterMode.IMMEDIATE_MODE
import aoc2019.toFile
import kotlin.math.pow

fun part1(): Int {

    val input = "input-day05.txt".toFile()
        .readLines()
        .first()
        .split(",")
        .map { it.toInt() }
        .toMutableList()
    println("input: $input")

    val computer = Computer(input, 1)
    computer.compute()
    return computer.diagnosticCode()
}

fun part2(): Int {

    val input = "input-day05.txt".toFile()
        .readLines()
        .first()
        .split(",")
        .map { it.toInt() }
        .toMutableList()
    println("input: $input")

    val computer = Computer(input, 5)
    computer.compute()
    return computer.diagnosticCode()
}

fun main() {

    val part1 = part1()
    println("part1: $part1")

    val part2 = part2()
    println("part2: $part2")
}

class Computer(private val input: MutableList<Int>, private val id: Int) {

    private val output = mutableSetOf<Int>()
    private var instructionPointer = 0

    fun compute() {
        while (true) {
            // println("instructionPointer: $instructionPointer")
            val number = input[instructionPointer]
            if (!number.hasOpCode()) {
                throw IllegalStateException("number $number has no valid op code")
            }

            val instruction = Instruction.of(number, instructionPointer)
            // println("number $number is $instruction")

            if (instruction.opCode.isTerminal) {
                println("$instruction is terminal instruction")
                break
            }

            apply(instruction)
        }
    }

    fun diagnosticCode() = output.last()

    private fun apply(instruction: Instruction) {

        var pointerModified = false

        when (instruction.opCode) {
            OP_CODE_01 -> input[param3(instruction)] = param1Value(instruction) + param2Value(instruction)
            OP_CODE_02 -> input[param3(instruction)] = param1Value(instruction) * param2Value(instruction)
            OP_CODE_03 -> input[param1(instruction)] = id
            OP_CODE_04 -> output.add(param1Value(instruction))
            OP_CODE_05 -> {
                if (param1ValueIsNonZero(instruction)) {
                    instructionPointer = param2Value(instruction)
                    pointerModified = true
                }
            }
            OP_CODE_06 -> {
                if (param1ValueIsZero(instruction)) {
                    instructionPointer = param2Value(instruction)
                    pointerModified = true
                }
            }
            OP_CODE_07 -> input[param3(instruction)] = if (param1IsLessThanParam2(instruction)) 1 else 0
            OP_CODE_08 -> input[param3(instruction)] = if (param1IsEqualToParam2(instruction)) 1 else 0
            OP_CODE_99 -> {}
        }
        if (!pointerModified) instructionPointer += instruction.parameterModes.size + 1
        // println("input: $input")
    }

    private fun param1(instruction: Instruction) = input[instruction.index + 1]
    private fun param3(instruction: Instruction) = input[instruction.index + 3]

    private fun paramValue(paramNumber: Int, instruction: Instruction) =
        if (instruction.parameterModes[paramNumber - 1] == IMMEDIATE_MODE)
            input[instruction.index + paramNumber] else input[input[instruction.index + paramNumber]]

    private fun param1Value(instruction: Instruction) = paramValue(1, instruction)
    private fun param2Value(instruction: Instruction) = paramValue(2, instruction)

    private fun param1ValueIsZero(instruction: Instruction) = param1Value(instruction) == 0
    private fun param1ValueIsNonZero(instruction: Instruction) = param1Value(instruction) != 0

    private fun param1IsEqualToParam2(instruction: Instruction) =
        param1Value(instruction) == param2Value(instruction)

    private fun param1IsLessThanParam2(instruction: Instruction) =
        param1Value(instruction) < param2Value(instruction)
}

enum class OpCode(val value: Int, val isTerminal: Boolean = false, val parameters: Int = 0) {
    OP_CODE_01(value = 1, parameters = 3),
    OP_CODE_02(value = 2, parameters = 3),
    OP_CODE_03(value = 3, parameters = 1),
    OP_CODE_04(value = 4, parameters = 1),
    OP_CODE_05(value = 5, parameters = 2),
    OP_CODE_06(value = 6, parameters = 2),
    OP_CODE_07(value = 7, parameters = 3),
    OP_CODE_08(value = 8, parameters = 3),
    OP_CODE_99(value = 99, isTerminal = true, parameters = 0);

    companion object {
        fun of(value: Int) = values().firstOrNull {
            (it.value == value || it.value == value % 10 || it.value == value % 100)
                    && value.toString().matches(Regex("^(?:([01]+0)*[1-8]|99)\$"))
        }
            ?: throw IllegalArgumentException("Illegal OpCode: $value")
    }
}

data class Instruction(val raw: Int, val index: Int, val opCode: OpCode, val parameterModes: List<ParameterMode>) {
    companion object {
        fun of(value: Int, index: Int): Instruction {
            val opCode: OpCode = OpCode.of(value)
            return Instruction(value, index, opCode, ParameterModes.of(value, opCode.parameters))
        }
    }
}

enum class ParameterMode {
    POSITION_MODE,
    IMMEDIATE_MODE;

    companion object {
        fun of(value: Int): ParameterMode =
            when (value) {
                0 -> POSITION_MODE
                1 -> IMMEDIATE_MODE
                else -> throw IllegalArgumentException("Illegal ParameterMode: $value")
            }
    }
}

/*
 * 12345 / 100 % 10     // C
 * 12345 / 1000 % 10    // B
 * 12345 / 10000 % 10   // A
 */
object ParameterModes {
    fun of(value: Int, parameters: Int): List<ParameterMode> {
        var cnt = 10.0.pow(parameters + 1)
        return (1..parameters).map {
            ParameterMode.of((value / cnt % 10).toInt())
                .also { cnt /= 10 }
        }.reversed()
    }
}

fun Int.hasOpCode() = try {
    OpCode.of(this)
    true
} catch (ex: IllegalArgumentException) {
    false
}