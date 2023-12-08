fun main() = day(true) { part, lines ->
    val (instr, n) = lines.joinToString("\n").split("\n\n")
    val nodes = n.split('\n').associate {
        val (source, rest) = it.split(" = ")
        val (left, right) = rest.removeSurrounding("(", ")").split(", ")
        source to (left to right)
    }
    val starts = nodes.keys.filter { it.endsWith('A') }
    val steps = LongArray(starts.size) { 0L }
    starts.forEachIndexed { index, start ->
        var current = start
        while (!current.endsWith('Z')) {
            val i = instr[(steps[index]++ % instr.length).toInt()]
            current = if (i == 'L') nodes[current]!!.first else nodes[current]!!.second
        }
    }
    tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
    fun lcm(a: Long, b: Long) = a * b / gcd(a, b)
    println(if (part.isOne) steps[starts.indexOf("AAA")] else steps.reduce(::lcm))
}