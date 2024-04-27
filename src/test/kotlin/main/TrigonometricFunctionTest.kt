package main

import ExpandableFunction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import provider.FunctionArgumentProvider
import triganometric.Cos
import triganometric.Cot
import triganometric.Csc
import triganometric.Sec
import triganometric.Sin
import triganometric.Tan
import util.mockFunction
import kotlin.math.abs
import kotlin.test.currentStackTrace

class TrigonometricFunctionTest {

    @Mock
    private lateinit var sin: Sin
    @Mock
    private lateinit var cos: Cos
    @Mock
    private lateinit var tan: Tan
    @Mock
    private lateinit var cot: Cot
    @Mock
    private lateinit var csc: Csc
    @Mock
    private lateinit var sec: Sec

    private lateinit var trigonometricFunction: TrigonometricFunction

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        trigonometricFunction = TrigonometricFunction(sin, cos, tan, cot, sec, csc)

        mockFunction(sin)
        mockFunction(cos)
        mockFunction(tan)
        mockFunction(cot)
        mockFunction(csc)
        mockFunction(sec)
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithValidValues(x: Double, y: Double) {
        // When
        val calculatedY = trigonometricFunction.calculate(x)

        // Then
        Assertions.assertTrue(abs(y - calculatedY) <= ExpandableFunction.EPS * 10) {
            "Calculated Y is not equivalent, expected: $y, actual: $calculatedY"
        }
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithInvalidValues(x: Double) {
        // When + Then
        assertThrows<IllegalArgumentException> { trigonometricFunction.calculate(x) }
    }
}