package aoc2019.day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun part1Test() {
        assertThat(part1()).isEqualTo(15508323)
    }

    @Test
    fun part2Test() {
        assertThat(part2()).isEqualTo(9006327)
    }
}