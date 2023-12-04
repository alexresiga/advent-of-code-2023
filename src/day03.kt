fun main() = day { part, lines ->
    var part1 = 0
    var part2 = 0
    val gears = mutableMapOf<Pair<Int, Int>, MutableSet<Int>>().withDefault { mutableSetOf() }
    lines.forEachIndexed { i, line ->
        var number = 0
        val positions = mutableListOf<Pair<Int, Int>>()
        var ok = false
        line.forEachIndexed { j: Int, c: Char ->
            if (c.isDigit()) {
                number = number * 10 + c.digitToInt()
                positions.add((i to j))
            } else if (number > 0) {
                for (p in positions) {
                    for (d in listOf((-1 to 0), (-1 to 1), (-1 to -1), (1 to -1), (1 to 0), (1 to 1), (0 to -1), (0 to 1))) {
                        val x = p.first + d.first
                        val y = p.second + d.second
                        if (x < 0 || x >= lines.size || y < 0 || y >= lines.size) continue
                        if (lines[x][y] != '.' && !lines[x][y].isDigit()) {
                            ok = true
                            if (lines[x][y] == '*') {
                                gears.putIfAbsent(x to y, mutableSetOf())
                                gears.getValue(x to y).add(number)
                            }
                            break
                        }
                    }
                }
                if (ok) part1 += number
                ok = false
                number = 0
                positions.clear()
            }
        }
    }
    part1.println()
    gears.values.filter { it.size == 2 }.sumOf { it.first() * it.last() }.println()
}
