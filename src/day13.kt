fun main() = day(name = "--- Day 13: Point of Incidence ---") { _, lines ->
    val patterns = lines.joinToString("\n").split("\n\n")
    var part1 = 0
    var part2 = 0

    patterns.map { it.split('\n') }.forEach { pattern ->
        fun f(size: Int, generator: (Int) -> String): Int {
            for (i in (0..<size - 1)) {
                var start = i
                var end = i + 1

                while (generator(start) == generator(end)) {
                    if (start == 0 || end == size - 1) {
                        return (i + 1)
                    }
                    start--
                    end++
                }
            }
            return 0
        }

        part1 += f(pattern.size) { i -> pattern[i] } * 100
        part1 += f(pattern.first().length) { j -> pattern.indices.map { i -> pattern[i][j] }.toString() }


        fun diff(index: Int): Int {
            var res = 0
            val dist = minOf(index, pattern.size - index)
            for (j in 0..<dist) {
                for (k in 0..<pattern.first().length) {
                    res += if (pattern[index + j][k] != pattern[index - j - 1][k]) 1 else 0
                }
            }
            return res
        }

        fun diffX(index: Int): Int {
            var res = 0
            val dist = minOf(index, pattern.first().length - index)
            for (j in 0..<dist) {
                for (k in pattern.indices) {
                    res += if (pattern[k][index + j] != pattern[k][index - j - 1]) 1 else 0
                }
            }
            return res
        }

        part2 += pattern.indices.sumOf { if (diff(it) == 1) it else 0 } * 100
        part2 += pattern.first().indices.sumOf { if (diffX(it) == 1) it else 0 }
    }
    println(part1)
    println(part2)
}



