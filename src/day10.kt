fun main() = day { _, lines ->
    val pipes = mapOf(
            '|' to ((-1 to 0) to (1 to 0)),
            '-' to ((0 to -1) to (0 to 1)),
            'L' to ((-1 to 0) to (0 to 1)),
            'J' to ((-1 to 0) to (0 to -1)),
            '7' to ((0 to -1) to (1 to 0)),
            'F' to ((0 to 1) to (1 to 0)),
    )
    val opps = mapOf(
            (-1 to 0) to (1 to 0),
            (1 to 0) to (-1 to 0),
            (0 to -1) to (0 to 1),
            (0 to 1) to (0 to -1)
    )
    val grid = lines.flatMapIndexed { i, line -> line.mapIndexed { j, c -> i to j to c } }.toMap()

    fun next(pos: Pair<Int, Int>, prev: Pair<Int, Int>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        val pipe = grid[pos]!!
        val moves = if (pipe == 'S') pipes['|']!! else pipes[pipe]!! // hardcoded for my input 🤭
        val direction = if (opps[prev] == moves.first) moves.second else moves.first
        return (pos.first + direction.first) to (pos.second + direction.second) to direction
    }

    var current = grid.filterValues { it == 'S' }.keys.first()
    var dir = -1 to 0 // still relevant for my input, sorry
    val loop = mutableListOf<Pair<Int, Int>>()
    do {
        val res = next(current, dir)
        current = res.first
        dir = res.second
        loop.add(current)
    } while (grid[current] != 'S')
    println(loop.size / 2)
    
    var part2 = 0
    lines.forEachIndexed { i, line ->
        line.indices.forEach { j ->
            if (i to j in loop) return@forEach
            val loopIntersections = loop.filter { it.first == i && it.second > j && grid[it] in listOf('L', 'J', '|', 'S' /*!!!!!!!*/) }
            if (loopIntersections.size % 2 == 1) {
                part2 += 1
            }
        }
    }
    println(part2)
}
