package triganometric

import ExpandableFunction

class Sec(
    private val sin: Sin,
    private val cos: Cos = Cos(sin),
) : ExpandableFunction {
    override fun calculate(x: Double): Double {
        var temp = x % (2 * Math.PI)
        if (x < 0) {
            temp += 2 * Math.PI
        }
        require(temp != Math.PI / 2 && temp != (Math.PI + Math.PI / 2)) { "x can't be pi*n - pi/2" }

        return 1 / cos.calculate(x)
    }
}