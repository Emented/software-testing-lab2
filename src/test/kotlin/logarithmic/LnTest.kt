package logarithmic

import ExpandableFunction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import provider.FunctionArgumentProvider
import kotlin.math.abs

class LnTest {

    private val ln = Ln()

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithValidValues(x: Double, y: Double) {
        // When
        val calculatedY = ln.calculate(x)

        // Then
        Assertions.assertTrue(abs(y - calculatedY) <= ExpandableFunction.EPS * 10) {
            "Calculated Y is not equivalent, expected: $y, actual: $calculatedY"
        }
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithInvalidValues(x: Double) {
        // When + Then
        assertThrows<IllegalArgumentException> { ln.calculate(x) }
    }
}