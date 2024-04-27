package logarithmic

import ExpandableFunction

class LogN(
    private val ln: Ln,
    private val logBase: Long,
) : ExpandableFunction {
    override fun calculate(x: Double): Double {
        require(x > 0) { "x should be greater than 0" }

        return ln.calculate(x) / ln.calculate(logBase.toDouble())
    }
}