package aoc2019.day01

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day01Test {

    @Test
    fun testRocketEquationPart1() = assertThat(rocketEquationPart1()).isEqualTo(3150224)

    @Test
    fun testRocketEquationPart2() = assertThat(rocketEquationPart2()).isEqualTo(4722484)
}