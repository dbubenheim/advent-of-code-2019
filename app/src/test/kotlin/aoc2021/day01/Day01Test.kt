package aoc2021.day01

import aoc2021.day01.Day01.Companion.sonarSweepPart1
import aoc2021.day01.Day01.Companion.sonarSweepPart2
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day01Test {

    @Test
    fun testSonarSweepPart1() = assertThat(sonarSweepPart1()).isEqualTo(1316)

    @Test
    fun testSonarSweepPart2() = assertThat(sonarSweepPart2()).isEqualTo(1344)
}