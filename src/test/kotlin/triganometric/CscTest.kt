package triganometric

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

class CscTest {

    @Mock
    private lateinit var sin: Sin

    private lateinit var csc: Csc

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        csc = Csc(sin)

        mockFunction(sin)
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithValidValues(x: Double, y: Double) {
        // When
        val calculatedY = csc.calculate(x)

        // Then
        Assertions.assertTrue(abs(y - calculatedY) <= ExpandableFunction.EPS) {
            "Calculated Y is not equivalent, expected: $y, actual: $calculatedY"
        }
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithInvalidValues(x: Double) {
        // When + Then
        assertThrows<IllegalArgumentException> { csc.calculate(x) }
    }
}