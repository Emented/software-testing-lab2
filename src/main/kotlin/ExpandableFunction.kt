import java.io.FileWriter
import java.io.PrintWriter

interface ExpandableFunction {
    fun calculate(
        x: Double,
    ): Double

    fun calculateAndWriteToCsv(x: Double) {
        val toPrint = "$x, ${calculate(x)}"
        val path = BASE_PATH_START + this.javaClass.simpleName + BASE_PATH_END
        val pw = PrintWriter(FileWriter(path, true))
        pw.println(toPrint)
        pw.close()
    }

    fun calculateSeriesAndWriteToCsv(xs: Array<Double>) {
        xs.forEach {
            calculateAndWriteToCsv(it)
        }
    }

    fun calculateWithStepAndWriteToCsv(
        from: Double,
        to: Double,
        step: Double,
    ) {
        require(from < to)
        require(step > 0)

        var start = from
        while (start <= to) {
            calculateAndWriteToCsv(start)
            start += step
        }
    }

    companion object {
        const val MAX_ITERATIONS = 1_000_000
        const val EPS = 0.000_000_000_1
        const val BASE_PATH_START: String = "out/"
        const val BASE_PATH_END: String = "-out.csv"
    }
}
