package aoc2019.day05

import aoc2019.day05.ParameterMode.IMMEDIATE_MODE
import aoc2019.day05.ParameterMode.POSITION_MODE
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ParameterModesTest {

    @ParameterizedTest
    @MethodSource("input")
    fun `test valid parameter modes`(input: Int, parameters: Int, expected: List<ParameterMode>) {
        assertThat(ParameterModes.of(input, parameters)).isEqualTo(expected)
    }

    companion object {

        @JvmStatic
        fun input(): Stream<Arguments> = Stream.of(
            Arguments.of(102, 3, listOf(IMMEDIATE_MODE, POSITION_MODE, POSITION_MODE)),
            Arguments.of(1002, 3, listOf(POSITION_MODE, IMMEDIATE_MODE, POSITION_MODE)),
            Arguments.of(11002, 3, listOf(POSITION_MODE, IMMEDIATE_MODE, IMMEDIATE_MODE)),
            Arguments.of(104, 1, listOf(IMMEDIATE_MODE)),
            Arguments.of(11101, 3, listOf(IMMEDIATE_MODE, IMMEDIATE_MODE, IMMEDIATE_MODE)),
            Arguments.of(1105, 2, listOf(IMMEDIATE_MODE, IMMEDIATE_MODE)),
            Arguments.of(2, 3, listOf(POSITION_MODE, POSITION_MODE, POSITION_MODE)),
            Arguments.of(99, 3, listOf(POSITION_MODE, POSITION_MODE, POSITION_MODE)),
        )
    }
}