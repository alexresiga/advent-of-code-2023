fun main() = day(true, "--- Day 12: Hot Springs ---") { part, lines ->
    lines.sumOf { line ->
        val cache = mutableMapOf<Pair<Int, Int>, Long>()

        val (ps, ns) = line.split(' ')
        val factor = if (part.isOne) 1 else 5
        val springs = buildString { repeat(factor) { append(ps); append('?') } }.dropLast(1)
        val groups = buildString { repeat(factor) { append(ns); append(',') } }.dropLast(1).split(',').map { it.toInt() }

        fun dp(p: Int, n: Int): Long {
            cache[p to n]?.let { return it }

            var ans = 0L
            if (p >= springs.length) return if (n == groups.size) 1 else 0
            if (springs[p] in listOf('.', '?')) {
                ans += dp(p + 1, n)
            }

            if (n == groups.size) return ans.also { cache[p to n] = ans }

            if (springs[p] in listOf('#', '?') &&
                    (p + groups[n] <= springs.length && '.' !in springs.substring(p, p + groups[n])) &&
                    (p + groups[n] == springs.length || '#' != springs[p + groups[n]])) {
                ans += dp(p + groups[n] + 1, n + 1)
            }
            return ans.also { cache[p to n] = ans }
        }
        dp(0, 0)
    }.println()
}