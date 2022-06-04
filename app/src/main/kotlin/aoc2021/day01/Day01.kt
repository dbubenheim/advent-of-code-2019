package aoc2021.day01

import aoc2021.toFile

class Day01 {

    companion object {

        fun sonarSweepPart1(): Int {
            val values = "input-day01.txt".toFile().readLines().map { it.toInt() }
            return values.windowed(2)
                .count { it[0] < it[1] }
        }

        fun sonarSweepPart2(): Int {
            val values = "input-day01.txt".toFile().readLines().map { it.toInt() }
            return values.windowed(3)
                .map { it.sum() }
                .zipWithNext()
                .count { it.first < it.second }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            println(sonarSweepPart1())
            println(sonarSweepPart2())
        }
    }
}