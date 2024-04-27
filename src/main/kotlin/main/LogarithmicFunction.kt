package main

import ExpandableFunction
import logarithmic.Ln
import logarithmic.LogN
import kotlin.math.pow

class LogarithmicFunction(
    private val ln: Ln,
    private val log3: LogN = LogN(ln, 3),
    private val log5: LogN = LogN(ln, 5),
    private val log10: LogN = LogN(ln, 10),
) : ExpandableFunction {
    override fun calculate(x: Double): Double {
        require(x > 0) { "x should be greater than 0" }
        require(x != 1.0) { "x should not be 1" }

        val result = (log3.calculate(x) - log10.calculate(x)) /
            log10.calculate(x) +
            log5.calculate(x) + log3.calculate(x) - ln.calculate(x)

        return result.pow(3)
    }
}