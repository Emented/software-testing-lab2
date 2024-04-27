package triganometric

import ExpandableFunction
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import provider.FunctionArgumentProvider
import kotlin.math.abs

class SinTest {

    private val sin = Sin()

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithValidValues(x: Double, y: Double) {
        // When
        val calculatedY = sin.calculate(x)

        // Then
        assertTrue(abs(y - calculatedY) <= ExpandableFunction.EPS) {
            "Calculated Y is not equivalent, expected: $y, actual: $calculatedY"
        }
    }
}