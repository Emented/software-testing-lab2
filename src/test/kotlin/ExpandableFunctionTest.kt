import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import triganometric.Sin
import java.io.File
import java.io.PrintWriter
import java.util.Scanner

class ExpandableFunctionTest {

    private val function = Sin()

    @Test
    fun testCalculateAndWriteToFileThenAppends() {
        // Given
        val filename = ExpandableFunction.BASE_PATH_START + "Sin" + ExpandableFunction.BASE_PATH_END

        val x = Math.PI
        val calculationResult = function.calculate(x)

        resetFile(filename)

        // When
        function.calculateAndWriteToCsv(x)

        // Then
        val scanner = Scanner(File(filename))
        var actual: String? = null
        while (scanner.hasNext()) {
            actual = scanner.nextLine()
        }

        assertEquals("$x, $calculationResult", actual)
    }

    @Test
    fun testCalculateSeriesAndWriteToFileThenAppends() {
        // Given
        val filename = ExpandableFunction.BASE_PATH_START + "Sin" + ExpandableFunction.BASE_PATH_END

        val xs = arrayOf(0.0, 0.5, 1.0)

        val expected = xs.map {
            "$it, ${function.calculate(it)}"
        }.toTypedArray()

        resetFile(filename)

        // When
        function.calculateSeriesAndWriteToCsv(xs)

        // Then
        val scanner = Scanner(File(filename))
        val actual: Array<String?> = Array(xs.size) { null }
        var i = 0
        while (scanner.hasNext()) {
            actual[i++] = scanner.nextLine()
        }

        assertArrayEquals(expected, actual)
    }

    @Test
    fun testCalculateWithStepAndWriteToCsvWrongBordersThenFails() {
        // Given
        val from = 2.0
        val to = 1.0
        val step = 0.2

        // When + Then
        assertThrows<IllegalArgumentException> { function.calculateWithStepAndWriteToCsv(from, to, step) }
    }

    @Test
    fun testCalculateWithStepAndWriteToCsvWrongStepThenFails() {
        // Given
        val from = 1.0
        val to = 2.0
        val step = 0.0

        // When + Then
        assertThrows<IllegalArgumentException> { function.calculateWithStepAndWriteToCsv(from, to, step) }
    }

    @Test
    fun testCalculateWithStepAndWriteToCsvThenAppends() {
        // Given
        val filename = ExpandableFunction.BASE_PATH_START + "Sin" + ExpandableFunction.BASE_PATH_END

        val from = 1.0
        val to = 2.0
        val step = 0.2

        resetFile(filename)

        // When
        function.calculateWithStepAndWriteToCsv(from, to, step)

        // Then
        val scanner = Scanner(File(filename))
        val result = mutableListOf<String>()
        while (scanner.hasNext()) {
            result.add(scanner.nextLine())
        }

        assertEquals(((to - from) / step).toInt() + 1, result.size)
    }

    private fun resetFile(filename: String) {
        val file = File(filename)
        val writer = PrintWriter(file)
        writer.print("")
        writer.close()
    }
}