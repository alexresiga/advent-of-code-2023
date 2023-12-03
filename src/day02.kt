fun main() = day { part, lines ->
    val bag = mapOf("red" to 12, "green" to 13, "blue" to 14)
    lines.withIndex().sumOf { (index, line) ->
        val sets = line.split(": ").last().split("; ").map { it.split(", ") }
        var ok = true
        val minBag = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        sets.forEach { set ->
            set.forEach {
                val (n, colour) = it.split(' ')
                when (part) {
                    Part.One -> if (n.toInt() > bag[colour]!!) ok = false
                    Part.Two -> minBag[colour] = maxOf(minBag[colour]!!, n.toInt())
                }
            }
        }
        when (part) {
            Part.One -> if (ok) index else 0
            Part.Two -> minBag.values.reduce(Int::times)
        }
    }.println()
}