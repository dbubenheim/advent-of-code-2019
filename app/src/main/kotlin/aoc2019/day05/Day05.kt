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

    val input = "input-day02.txt".toFile()
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
            println("instructionPointer: $instructionPointer")
            val number = input[instructionPointer]
            if (!number.hasOpCode()) {
                throw IllegalStateException("number $number has no valid op code")
            }

            val instruction = Instruction.of(number, instructionPointer)
            println("number $number is $instruction")

            if (instruction.opCode.isTerminal) {
                println("$instruction is terminal instruction")
                break
            }

            apply(instruction)
        }
    }

    private fun apply(instruction: Instruction) {
        val index = instruction.index
        val firstParam = input[index + 1]
        println("firstParam: $firstParam")
        val secondParam = input[index + 2]
        println("secondParam: $secondParam")
        val thirdParam = input[index + 3]
        println("thirdParam: $thirdParam")
        val parameterModes = instruction.parameterModes
        var pointerModified = false
        when (instruction.opCode) {
            OP_CODE_01 -> {
                val newValue = firstParam(parameterModes, firstParam) + secondParam(parameterModes, secondParam)
                println("newValue: $newValue")
                input[thirdParam] = newValue
            }
            OP_CODE_02 -> {
                val newValue = firstParam(parameterModes, firstParam) * secondParam(parameterModes, secondParam)
                println("newValue: $newValue")
                input[thirdParam] = newValue
            }
            OP_CODE_03 -> input[firstParam] = id
            OP_CODE_04 -> {
                val value = firstParam(parameterModes, firstParam)
                println("OUTPUT: $value")
                output.add(value)
            }
            OP_CODE_05 -> {
                if (firstParam(parameterModes, firstParam) != 0) {
                    instructionPointer = secondParam(parameterModes, secondParam)
                    pointerModified = true
                }
            }
            OP_CODE_06 -> {
                if (firstParam(parameterModes, firstParam) == 0) {
                    instructionPointer = secondParam(parameterModes, secondParam)
                    pointerModified = true
                }
            }
            OP_CODE_07 -> {
                val lt = firstParam(parameterModes, firstParam) < secondParam(parameterModes, secondParam)
                input[thirdParam] = if (lt) 1 else 0
            }
            OP_CODE_08 -> {
                val eq = firstParam(parameterModes, firstParam) == secondParam(parameterModes, secondParam)
                input[thirdParam] = if (eq) 1 else 0
            }
            OP_CODE_99 -> {}
        }
        if (!pointerModified) instructionPointer += instruction.parameterModes.size + 1
        println("input: $input")
    }

    private fun secondParam(
        parameterModes: List<ParameterMode>,
        secondParam: Int,
    ) = if (parameterModes[1] == IMMEDIATE_MODE) secondParam else input[secondParam]

    private fun firstParam(
        parameterModes: List<ParameterMode>,
        firstParam: Int,
    ) = if (parameterModes[0] == IMMEDIATE_MODE) firstParam else input[firstParam]

    fun diagnosticCode() = output.last()
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
                    && value.toString().matches(Regex("^(?:(?:([01]+0)*[1-8])|99)\$"))
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