package main

import ExpandableFunction
import logarithmic.Ln
import logarithmic.LogN
import triganometric.Cos
import triganometric.Cot
import triganometric.Csc
import triganometric.Sec
import triganometric.Sin
import triganometric.Tan

class MainFunction(
    private val ln: Ln = Ln(),
    private val log3: LogN = LogN(ln, 3),
    private val log5: LogN = LogN(ln, 5),
    private val log10: LogN = LogN(ln, 10),

    private val sin: Sin = Sin(),
    private val cos: Cos = Cos(sin),
    private val tan: Tan = Tan(sin),
    private val cot: Cot = Cot(sin),
    private val sec: Sec = Sec(sin),
    private val csc: Csc = Csc(sin),

    private val logarithmicFunction: LogarithmicFunction = LogarithmicFunction(ln, log3, log5, log10),
    private val trigonometricFunction: TrigonometricFunction = TrigonometricFunction(sin, cos, tan, cot, sec, csc),
) : ExpandableFunction {
    override fun calculate(x: Double): Double {
        return if (x <= 0) {
            trigonometricFunction.calculate(x)
        } else {
            logarithmicFunction.calculate(x)
        }
    }
}