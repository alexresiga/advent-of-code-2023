fun main() = day(true) { part, lines ->
    val (times, distances) = lines.map {
        it.substringAfter(":")
                .let { if (part.isTwo) it.replace(" ", "") else it }
                .split(' ')
                .filter { it.isNotBlank() }
                .map { it.toLong() }
    }
    times.zip(distances).fold(1L) { acc, (time, distance) ->
        acc * (1..<time).count { it * (time - it) > distance }
    }.println()
}