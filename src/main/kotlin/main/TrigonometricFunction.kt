package main

import ExpandableFunction
import triganometric.Cos
import triganometric.Cot
import triganometric.Csc
import triganometric.Sec
import triganometric.Sin
import triganometric.Tan
import kotlin.math.pow

class TrigonometricFunction(
    private val sin: Sin,
    private val cos: Cos = Cos(sin),
    private val tan: Tan = Tan(sin),
    private val cot: Cot = Cot(sin),
    private val sec: Sec = Sec(sin),
    private val csc: Csc = Csc(sin),
) : ExpandableFunction {

    /*
    function period: 2pi
    assimptotics:
        -1.571 - 2pi*k
        -pi*k
     */
    override fun calculate(x: Double): Double {
        require(x < 0) { "x should be less then 0" }

        // x != -1.571 - 2pi*k
        var temp = x % (2 * Math.PI)
        require(Math.round(temp * 1000).toDouble() / 1000 != -1.571) { "x can't be -1.571 - 2pi*k" }

        // x != -pi*k
        temp = x % Math.PI
        require(Math.round(temp * 1000).toDouble() / 1000 != 0.0) { "x can't be -pi*k" }

        return ((((((((((((((((((csc.calculate(x) * tan.calculate(x)) - tan.calculate(x)) + csc.calculate(x)) /
            tan.calculate(x)) /
            cos.calculate(x)) / sin.calculate(x)) * (tan.calculate(x) * (cot.calculate(x) - (sin.calculate(x) /
            sin.calculate(x))))).pow(3)) * sec.calculate(x)).pow(2)) / tan.calculate(x)) - sin.calculate(x)) -
            sec.calculate(x)) * cos.calculate(x)).pow(2)) *
            (((cos.calculate(x).pow(2)) - (cot.calculate(x) / (tan.calculate(x) / sec.calculate(x)))).pow(2))) /
            ((((csc.calculate(x) - csc.calculate(x)) - csc.calculate(x)) *
                ((((((((csc.calculate(x).pow(2)) - cos.calculate(x)) * csc.calculate(x)) *
                    (((tan.calculate(x) + cos.calculate(x)).pow(2)) / sec.calculate(x))) *
                    csc.calculate(x)).pow(2)).pow(3)) / (cot.calculate(x) / (((csc.calculate(x) /
                    cos.calculate(x)) /
                    (((cot.calculate(x) - csc.calculate(x)) + (cot.calculate(x) + cos.calculate(x))) *
                        tan.calculate(x))) - sin.calculate(x))))) + (((((((cot.calculate(x) *
                sin.calculate(x)).pow(3)) + cot.calculate(x)).pow(2)).pow(3)).pow(2)) /
                sin.calculate(x)))) + (cos.calculate(x) + ((((sec.calculate(x) * sin.calculate(x)) *
            (((sec.calculate(x) * (cos.calculate(x) * tan.calculate(x))) - csc.calculate(x)) *
                ((sin.calculate(x) + (csc.calculate(x) - ((cos.calculate(x) + cot.calculate(x)) *
                    tan.calculate(x)))) - (((tan.calculate(x) + tan.calculate(x)) / sin.calculate(x)) *
                    csc.calculate(x))))) - (sec.calculate(x).pow(3))) * ((tan.calculate(x) * cos.calculate(x)) *
            sin.calculate(x)))))
    }
}