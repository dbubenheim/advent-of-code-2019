package aoc2019.day02

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day02Test {

    @Test
    fun part1Test() {
        assertThat(part1()).isEqualTo(5866663)
    }

    @Test
    fun part2Test() {
        assertThat(part2()).isEqualTo(4259)
    }
}