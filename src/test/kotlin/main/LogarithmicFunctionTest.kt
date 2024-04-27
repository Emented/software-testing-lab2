package main

import logarithmic.Ln
import logarithmic.LogN
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import provider.FunctionArgumentProvider
import util.mockFunction
import kotlin.math.abs

class LogarithmicFunctionTest {

    @Mock
    private lateinit var ln: Ln
    @Mock
    private lateinit var log3: LogN
    @Mock
    private lateinit var log5: LogN
    @Mock
    private lateinit var log10: LogN

    private lateinit var logarithmicFunction: LogarithmicFunction

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        
        logarithmicFunction = LogarithmicFunction(ln, log3, log5, log10)
        
        mockFunction(ln)
        mockFunction(log3, "log3")
        mockFunction(log5, "log5")
        mockFunction(log10, "log10")
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithValidValues(x: Double, y: Double) {
        // When
        val calculatedY = logarithmicFunction.calculate(x)

        // Then
        Assertions.assertTrue(abs(y - calculatedY) <= ExpandableFunction.EPS * 10) {
            "Calculated Y is not equivalent, expected: $y, actual: $calculatedY"
        }
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithInvalidValues(x: Double) {
        // When + Then
        assertThrows<IllegalArgumentException> { logarithmicFunction.calculate(x) }
    }

}