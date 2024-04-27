package triganometric

import ExpandableFunction
import kotlin.math.pow
import kotlin.math.sqrt

class Cos(
    private val sin: Sin
) : ExpandableFunction {
    override fun calculate(x: Double): Double {
        var temp = x % (2 * Math.PI)
        if (x < 0) {
            temp += 2 * Math.PI
        }

        return if (temp > Math.PI / 2 && temp < (Math.PI + Math.PI / 2)) {
            -sqrt(1 - sin.calculate(x).pow(2))
        } else {
            val sinSq = sin.calculate(x).pow(2)
            sqrt(1 - sinSq)
        }
    }
}
