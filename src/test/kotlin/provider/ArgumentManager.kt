package provider

import org.junit.jupiter.params.provider.Arguments
import java.io.File
import java.util.Scanner
import java.util.stream.Stream

object ArgumentsManager {
    private const val VALID_TABLE_DIRECTORY = "./src/test/resources/tables/valid/"
    private const val INVALID_TABLE_DIRECTORY = "./src/test/resources/tables/invalid/"

    fun getAllDots(filename: String): Map<Double, Double> {
        return streamFromDotsTable(filename).reduce(
            mutableMapOf(), { map, argument ->
                map[argument.get()[0] as Double] = argument.get()[1] as Double
                map
            },
            { map1, map2 ->
                map1.putAll(map2)
                map1
            }
        )
    }

    fun streamFromDotsTable(filename: String, isValid: Boolean = true): Stream<Arguments> {
        val arguments = mutableListOf<Arguments>()

        val tableDirectory = when (isValid) {
            true -> VALID_TABLE_DIRECTORY
            false -> INVALID_TABLE_DIRECTORY
        }

        val scanner = Scanner(File(tableDirectory + filename))

        scanner.nextLine()

        while (scanner.hasNext()) {
            val cur = scanner.nextLine()

            if (cur.contains("#")) continue

            if (isValid) {

                val splitCur = cur.split(",")

                val x = splitCur[0].toDouble()
                val y = splitCur[1].toDouble()

                arguments.add(Arguments.of(x, y))
            } else {
                val x = cur.toDouble()

                arguments.add(Arguments.of(x))
            }
        }

        return arguments.stream()
    }
}