
interface Frame {
    fun getValue(): Int = 0
    fun getFirstValue(): Int = 0
    fun getSecondValue(): Int = 0
}

class Strike : Frame {
    override fun getValue(): Int = 10
    override fun getFirstValue(): Int = 10
}

abstract class NormalFrame : Frame {
    var first: Int = 0
    var second: Int = 0

    override fun getValue(): Int = first + second
    override fun getFirstValue(): Int = first
    override fun getSecondValue(): Int = second
}

class Spare(str: String) : NormalFrame() {
    init {
        first = str[0].digitToInt()
        second = 10 - first
    }
}

class Regular(str: String) : NormalFrame() {
    init {
        first = str[0].digitToInt()
        if(str.length == 2) {
            second = str.replace('-', '0')[1].digitToInt()
        }
    }
}

class LastFrame(str: String) : Frame {
    private val first: Int
    private val second: Int
    private val third: Int

    init {
        val partialFrame = parseNormalFrame(str.substring(0,2))
        first = partialFrame.getFirstValue()
        second = partialFrame.getSecondValue()
        third = parseNormalFrame(str.substring(2)).getFirstValue()
    }

    override fun getValue(): Int = first + second + third
    override fun getFirstValue(): Int = first
}

private const val SPACE_CHAR = ' '

fun calculateScore(str: String): Int {
    if (str.isBlank()) {
        throw IllegalArgumentException("Input is empty")
    }

    str.split(SPACE_CHAR).size

    val frames: List<Frame> = str
        .split(SPACE_CHAR)
        .map { parseFrame(it) }

    if (frames.size != 10) {
        throw IllegalArgumentException("Frame count is not 10")
    }

    var result = 0
    for ((index, value) in frames.withIndex()) {
        result += value.getValue()
        if (value is Spare) {
            result += if (index < 9) frames[index + 1].getFirstValue() else 0
        }
        if(value is Strike) {
            result += if (index < 9) frames[index + 1].getValue() else 0
            result += if (index < 8) frames[index + 2].getValue() else 0
        }
    }

    return result
}

private fun parseFrame(str: String): Frame {
    return when (str.length) {
        1 -> Strike()
        2 -> parseNormalFrame(str)
        3 -> LastFrame(str)
        else -> throw IllegalArgumentException("Invalid Frame format")
    }
}

private fun parseNormalFrame(str: String): Frame {
    return if (isStrike(str)) Strike()
    else if (isSpare(str)) Spare(str)
    else Regular(str)
}

private fun isStrike(str: String) = str.contains('X')

private fun isSpare(str: String) = str.contains('/')


fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}