
enum class Bowling(val value: Char) {
    STRIKE('X'), SPARE('/'), MISS ('-'),
}

interface Frame
data class Strike(val value: Int = 10) : Frame
data class Spare(val first: Int, val second: Int) : Frame
data class Regular(val first: Int, val second: Int = 0) : Frame
// TODO LastFrame is something special.... (need to figure it out)

fun calculateScore(s: String): Int {
    if(s.isBlank()) {
        return 0
    }

    val frames: List<Frame> = s
        .split(" ")
        .map {
            if(it[0] == 'X') Strike()
            else if(it[1] == '/') Spare(it[0].digitToInt(), 10 - it[0].digitToInt())
            else if(it[1] == '-') Regular(it[0].digitToInt())
            else Regular(it[0].digitToInt(), it[1].digitToInt())
        }

    return -1
}


fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}