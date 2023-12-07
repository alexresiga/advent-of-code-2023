import kotlin.io.path.Path
import kotlin.io.path.readLines

enum class Part {
    One, Two;

    val isOne: Boolean
        get() = this == One
    val isTwo: Boolean
        get() = this == Two
}

inline fun <reified T> T.println(): T = also { println(it) }

inline fun day(both: Boolean = false, block: (part: Part, input: List<String>) -> Any?) {
    val input = Path("src/data.in").readLines()
    Part.entries.forEach { block(it, input); if (!both) return }
}
