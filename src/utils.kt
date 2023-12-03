import java.io.BufferedReader
import kotlin.io.path.Path
import kotlin.io.path.bufferedReader
import kotlin.io.path.inputStream

enum class Part {
    One, Two;

    val isOne: Boolean
        get() = this == One
    val isTwo: Boolean
        get() = this == Two
}

inline fun <reified T> T.println(): T = also { println(it) }

inline fun day(block: (part: Part, input: List<String>) -> Any?) {
    val input = Path("src/data.in").inputStream().bufferedReader().readLines()
    block(Part.One, input)
    block(Part.Two, input)
}
