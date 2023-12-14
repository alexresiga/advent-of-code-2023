fun main() = day(true, "--- Day 14: Parabolic Reflector Dish ---") { part, lines ->
    val grid = lines.flatMapIndexed { i, line -> line.mapIndexed { j, c -> i to j to c } }.toMap().toMutableMap()
    for (i in lines.indices) {
        for (j in lines.first().indices) {
            grid[i to j] = lines[i][j]
        }
    }
    repeat(if (part.isOne) 1 else 300 /* random */) {
        var part1 = 0
        // north
        for (i in lines.indices.drop(1)) {
            for (j in lines.first().indices) {
                if (grid[i to j] == 'O') {
                    var ii = i - 1
                    while (ii >= 0 && grid[ii to j] !in listOf('#', 'O')) {
                        grid[ii to j] = 'O'
                        grid[ii + 1 to j] = '.'
                        ii--
                    }
                }
            }
        }
        if (part.isOne) {
            grid.forEach { (k, v) ->
                if (v == 'O') {
                    part1 += lines.size - k.first
                }
            }
            println(part1)
            return@repeat
        }
        // west
        for (j in lines.first().indices.drop(1)) {
            for (i in lines.indices) {
                if (grid[i to j] == 'O') {
                    var jj = j - 1
                    while (jj >= 0 && grid[i to jj] !in listOf('#', 'O')) {
                        grid[i to jj] = 'O'
                        grid[i to jj + 1] = '.'
                        jj--
                    }
                }
            }
        }
        // south 
        for (i in lines.indices.sortedDescending()) {
            for (j in lines.first().indices) {
                if (grid[i to j] == 'O') {
                    var ii = i + 1
                    while (ii < lines.size && grid[ii to j] !in listOf('#', 'O')) {
                        grid[ii to j] = 'O'
                        grid[ii - 1 to j] = '.'
                        ii++
                    }
                }
            }
        }

        // east
        for (j in lines.first().indices.sortedDescending().drop(1)) {
            for (i in lines.indices) {
                if (grid[i to j] == 'O') {
                    var jj = j + 1
                    while (jj < lines.first().length && grid[i to jj] !in listOf('#', 'O')) {
                        grid[i to jj] = 'O'
                        grid[i to jj - 1] = '.'
                        jj++
                    }
                }
            }
        }
        grid.forEach { (k, v) ->
            if (v == 'O') {
                part1 += lines.size - k.first
            }
        }
        if (part.isTwo) {
            // i found the cycle manually while on the plane 
            val magic = (1000000000 - 124) % (82 - 124) + 124 - 1
            if (it == magic) println(part1)
        }
    }
}