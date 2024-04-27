package triganometric

import ExpandableFunction
import ExpandableFunction.Companion.EPS
import ExpandableFunction.Companion.MAX_ITERATIONS
import kotlin.math.abs
import kotlin.math.pow

class Sin : ExpandableFunction {
    override fun calculate(x: Double): Double {
        var result = x
        var curSum = 1.0

        var n = 1L

        while (abs(curSum) >= EPS && n - 1 <= MAX_ITERATIONS) {
            curSum = (-1.0).pow(n.toDouble()) * x.pow((2 * n + 1).toDouble()) / getFactorial(2 * n + 1)
            result += curSum
            n++
        }

        return result
    }

    private fun getFactorial(n: Long): Double {
        var result = 1.0
        for (i in 1..n) {
            result *= i
        }
        return result
    }
}
