fun main() = day(true) { part, lines ->
    lines.sumOf { line ->
        val elements = mutableListOf<Int>()
        var sequence = line.split(' ').map { it.toInt() }
        while (!sequence.all { it == 0 }) {
            elements.add(if (part.isOne) sequence.last() else sequence.first())
            sequence = sequence.zipWithNext().map { (a, b) -> b - a }
        }
        if (part.isOne) elements.sum() else elements.foldRight(0) { acc, e -> acc - e }
    }.println()
}