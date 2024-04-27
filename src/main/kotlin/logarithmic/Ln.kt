package logarithmic

import ExpandableFunction
import ExpandableFunction.Companion.EPS
import ExpandableFunction.Companion.MAX_ITERATIONS
import kotlin.math.abs
import kotlin.math.pow

class Ln : ExpandableFunction {
    override fun calculate(x: Double): Double {
        require(x > 0) { "x should be greater than 0" }

        var n = 1
        var result = 0.0
        var approx = 1.0
        var curIteration = 0

        while (abs(approx) >= EPS && curIteration <= MAX_ITERATIONS) {
            approx = ((1 - x) / (1 + x)).pow(n.toDouble()) / n
            result += approx
            n += 2
            curIteration++
        }

        return result * -2
    }
}