package logarithmic

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
import util.mockFunction
import kotlin.math.abs

class Log10Test {

    @Mock
    private lateinit var ln: Ln

    private lateinit var log10: LogN

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        log10 = LogN(ln, 10)

        mockFunction(ln)
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithValidValues(x: Double, y: Double) {
        // When
        val calculatedY = log10.calculate(x)

        // Then
        Assertions.assertTrue(abs(y - calculatedY) <= ExpandableFunction.EPS * 10) {
            "Calculated Y is not equivalent, expected: $y, actual: $calculatedY"
        }
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithInvalidValues(x: Double) {
        // When + Then
        assertThrows<IllegalArgumentException> { log10.calculate(x) }
    }
}