package util

import ExpandableFunction
import org.mockito.Mockito
import provider.FunctionArgumentProvider

fun mockFunction(function: ExpandableFunction, functionName: String? = null) {
    val stubValues = FunctionArgumentProvider.getAllDots(functionName ?: function.javaClass.simpleName)

    stubValues.forEach {
        Mockito.`when`(function.calculate(it.key)).thenReturn(it.value)
    }
}