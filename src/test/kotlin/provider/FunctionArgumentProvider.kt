package provider

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

class FunctionArgumentProvider : ArgumentsProvider {

    override fun provideArguments(extensionContext: ExtensionContext): Stream<Arguments> {
        val contextName = extensionContext.testClass.get().simpleName
        val isValid = !extensionContext.testMethod.get().name.contains(INVALID_KEY_WORD)
        val functionName = contextName.substring(0, contextName.length - TEST_POSTFIX_LENGTH)
        return ArgumentsManager.streamFromDotsTable(functionName + TABLE_POSTFIX, isValid)
    }

    companion object {
        private const val TABLE_POSTFIX = "Table.csv"
        private const val TEST_POSTFIX_LENGTH = 4
        private const val INVALID_KEY_WORD = "Invalid"

        fun getAllDots(functionName: String): Map<Double, Double> {
            try {
                return ArgumentsManager.getAllDots(functionName + TABLE_POSTFIX)
            } catch (e: Exception) {
                throw RuntimeException("There is no y's for function with name: $functionName")
            }
        }
    }
}