fun main() = day { part, lines ->
    val digits = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
    val pattern = if (part.isTwo) "(?=(\\d|one|two|three|four|five|six|seven|eight|nine))" else "\\d"
    lines.sumOf { line ->
        val matches = Regex(pattern).findAll(line)
        when (part) {
            Part.One -> matches.map { it.value }
            Part.Two -> matches.map { it.groupValues[1] }.map { if (it.length == 1) it else digits[it] }
        }.let { "${it.first()}${it.last()}".toInt() }
    }.println()
}