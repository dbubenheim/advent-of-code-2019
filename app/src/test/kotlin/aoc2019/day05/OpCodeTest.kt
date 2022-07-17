package aoc2019.day05

import aoc2019.day05.OpCode.OP_CODE_01
import aoc2019.day05.OpCode.OP_CODE_02
import aoc2019.day05.OpCode.OP_CODE_03
import aoc2019.day05.OpCode.OP_CODE_04
import aoc2019.day05.OpCode.OP_CODE_05
import aoc2019.day05.OpCode.OP_CODE_06
import aoc2019.day05.OpCode.OP_CODE_07
import aoc2019.day05.OpCode.OP_CODE_08
import aoc2019.day05.OpCode.OP_CODE_99
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

class OpCodeTest {

    @ParameterizedTest
    @ValueSource(
        ints = [
            MIN_VALUE, -1000, -100, -50, -20, -10, -1, 0, 9, 10, 100, 1000, 1100, 1011, 22, 599, 99999, 1099, 1199,
            10099, 100099, 111999, MAX_VALUE
        ]
    )
    fun `test for failure`(input: Int) {
        assertThat { OpCode.of(input) }.isFailure()
            .hasClass(IllegalArgumentException::class)
    }

    @ParameterizedTest
    @MethodSource("opCodeInput")
    fun `test op code from int input`(input: Int, expected: OpCode) {
        assertThat(OpCode.of(input)).isEqualTo(expected)
    }

    companion object {

        @JvmStatic
        fun opCodeInput(): Stream<Arguments> = Stream.of(
            Arguments.of(11101, OP_CODE_01),
            Arguments.of(11001, OP_CODE_01),
            Arguments.of(10101, OP_CODE_01),
            Arguments.of(10001, OP_CODE_01),
            Arguments.of(1101, OP_CODE_01),
            Arguments.of(1001, OP_CODE_01),
            Arguments.of(101, OP_CODE_01),
            Arguments.of(1, OP_CODE_01),

            Arguments.of(11102, OP_CODE_02),
            Arguments.of(11002, OP_CODE_02),
            Arguments.of(10102, OP_CODE_02),
            Arguments.of(10002, OP_CODE_02),
            Arguments.of(1102, OP_CODE_02),
            Arguments.of(1002, OP_CODE_02),
            Arguments.of(102, OP_CODE_02),
            Arguments.of(2, OP_CODE_02),

            Arguments.of(103, OP_CODE_03),
            Arguments.of(3, OP_CODE_03),

            Arguments.of(104, OP_CODE_04),
            Arguments.of(4, OP_CODE_04),

            Arguments.of(1105, OP_CODE_05),
            Arguments.of(1005, OP_CODE_05),
            Arguments.of(105, OP_CODE_05),
            Arguments.of(5, OP_CODE_05),

            Arguments.of(1106, OP_CODE_06),
            Arguments.of(1006, OP_CODE_06),
            Arguments.of(106, OP_CODE_06),
            Arguments.of(6, OP_CODE_06),

            Arguments.of(11107, OP_CODE_07),
            Arguments.of(11007, OP_CODE_07),
            Arguments.of(10107, OP_CODE_07),
            Arguments.of(10007, OP_CODE_07),
            Arguments.of(1107, OP_CODE_07),
            Arguments.of(1007, OP_CODE_07),
            Arguments.of(107, OP_CODE_07),
            Arguments.of(7, OP_CODE_07),

            Arguments.of(11108, OP_CODE_08),
            Arguments.of(11008, OP_CODE_08),
            Arguments.of(10108, OP_CODE_08),
            Arguments.of(10008, OP_CODE_08),
            Arguments.of(1108, OP_CODE_08),
            Arguments.of(1008, OP_CODE_08),
            Arguments.of(108, OP_CODE_08),
            Arguments.of(8, OP_CODE_08),

            Arguments.of(99, OP_CODE_99),
        )
    }
}