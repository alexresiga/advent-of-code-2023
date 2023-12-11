import kotlin.math.abs

fun main() = day(true) { part, lines ->
    val factor = (if (part.isOne) 2 else 1000000) - 1
    val rows = lines.mapIndexedNotNull { i, line -> if (line.all { it == '.' }) i else null }
    val cols = lines.first().indices.mapNotNull { j -> if (lines.indices.all { lines[it][j] == '.' }) j else null }
    val points = lines.flatMapIndexed { i, line -> line.mapIndexedNotNull { j, c -> if (c == '#') i to j else null } }

    fun Pair<Int, Int>.distance(b: Pair<Int, Int>): Int = abs(first - b.first) + abs(second - b.second)
    fun List<Int>.countIn(a: Pair<Int, Int>, b: Pair<Int, Int>): Long = count { it in minOf(a.first, b.first)..maxOf(a.first, b.first) }.toLong()
    
    points.indices.sumOf { p1 ->
        (p1 + 1..<points.size).sumOf { p2 ->
            points[p1].distance(points[p2]) + rows.countIn(points[p1], points[p2]) * factor + cols.countIn(points[p1], points[p2]) * factor
        }
    }.println()
}