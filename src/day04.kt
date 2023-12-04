import kotlin.math.pow

fun main() = day { _, lines ->
    val copies = lines.indices.associateWith { 1 }.toMutableMap()
    lines.withIndex().sumOf { (index, line) ->
        val (have, win) = line.substringAfter(": ").split(" | ")
                .map { it.trim().split(' ').filter { it.isNotBlank() }.map { it.toInt() }.toSet() }
        val matches = win.intersect(have).size
        for (i in index + 1..index + matches) {
            copies[i] = copies[i]!! + copies[index]!!
        }
        2f.pow(matches - 1).toInt()
    }.println()
    copies.values.sum().println()
}