package triganometric

import ExpandableFunction

class Csc(
    private val sin: Sin,
) : ExpandableFunction {
    override fun calculate(x: Double): Double {
        var temp = x % (2 * Math.PI)
        if (x < 0) {
            temp += 2 * Math.PI
        }
        require(!(temp == 0.0 || temp == Math.PI || temp == 2 * Math.PI)) { "x can't be pi*n" }

        return 1 / sin.calculate(x)
    }
}