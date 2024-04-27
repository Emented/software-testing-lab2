package main

import ExpandableFunction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import provider.FunctionArgumentProvider
import util.mockFunction
import kotlin.math.abs

class MainFunctionTest {

    @Mock
    private lateinit var trigonometricFunction: TrigonometricFunction
    @Mock
    private lateinit var logarithmicFunction: LogarithmicFunction

    private lateinit var mainFunction: MainFunction

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        mainFunction = MainFunction(
            trigonometricFunction = trigonometricFunction,
            logarithmicFunction =  logarithmicFunction,
        )

        mockFunction(trigonometricFunction)
        mockFunction(logarithmicFunction)
    }

    @ParameterizedTest
    @ArgumentsSource(FunctionArgumentProvider::class)
    fun testWithValidValues(x: Double, y: Double) {
        // When
        val calculatedY = mainFunction.calculate(x)

        // Then
        Assertions.assertTrue(abs(y - calculatedY) <= ExpandableFunction.EPS * 10) {
            "Calculated Y is not equivalent, expected: $y, actual: $calculatedY"
        }
    }
}