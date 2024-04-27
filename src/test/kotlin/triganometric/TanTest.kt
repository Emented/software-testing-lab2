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

class TanTest {

    @Mock
    private lateinit var sin: Sin

    @Mock
    private lateinit var cos: Cos

    private lateinit var tan: Tan

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        tan = Tan(sin, cos)

        mockFunction(sin)
        mockFunction(cos)
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithValidValues(x: Double, y: Double) {
        // When
        val calculatedY = tan.calculate(x)

        // Then
        Assertions.assertTrue(abs(y - calculatedY) <= ExpandableFunction.EPS) {
            "Calculated Y is not equivalent, expected: $y, actual: $calculatedY"
        }
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithInvalidValues(x: Double) {
        // When + Then
        assertThrows<IllegalArgumentException> { tan.calculate(x) }
    }
}