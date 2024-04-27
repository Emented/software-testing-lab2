package triganometric

import ExpandableFunction

class Cot(
    private val sin: Sin,
    private val cos: Cos = Cos(sin),
) : ExpandableFunction {
    override fun calculate(x: Double): Double {
        var temp = x % (2 * Math.PI)
        if (x < 0) {
            temp += 2 * Math.PI
        }
        require(temp != 0.0 && temp != Math.PI && temp != 2 * Math.PI) { "x can't be pi*n" }

        return cos.calculate(x) / sin.calculate(x)
    }
}