package aoc2019.day05

import aoc2019.day05.ParameterMode.IMMEDIATE_MODE
import aoc2019.day05.ParameterMode.POSITION_MODE
import assertk.assertThat
import assertk.assertions.hasClass
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.Int.Companion.MAX_VALUE
import kotlin.Int.Companion.MIN_VALUE

internal class ParameterModeTest {

    @ParameterizedTest
    @MethodSource("input")
    fun `test valid parameter modes`(input: Int, expected: ParameterMode) {
        assertThat(ParameterMode.of(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(
        ints = [MIN_VALUE, -5000, -1000, -100, -10, -5, -2, -1, 2, 5, 10, 100, 200, 500, 1000, 5000, MAX_VALUE]
    )
    fun `test invalid parameter modes`(input: Int) {
        assertThat { ParameterMode.of(input) }
            .isFailure()
            .hasClass(IllegalArgumentException::class)
    }

    companion object {

        @JvmStatic
        fun input(): Stream<Arguments> = Stream.of(
            Arguments.of(0, POSITION_MODE),
            Arguments.of(1, IMMEDIATE_MODE)
        )
    }
}