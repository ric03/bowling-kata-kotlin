import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MainKtTest : StringSpec({

    "calculateScore should return IllegalArgumentException given empty string" {
        shouldThrow<IllegalArgumentException> {
            calculateScore("")
        }
    }

    "calculateScore should return IllegalArgumentException given less than 10 frames" {
        shouldThrow<IllegalArgumentException> {
            calculateScore("1- 2- 3- 4- 5- 6- 7- 8- 9-")
        }
    }

    "calculateScore should return IllegalArgumentException given more than 10 frames" {
        shouldThrow<IllegalArgumentException> {
            calculateScore("1- 2- 3- 4- 5- 6- 7- 8- 9- x 11")
        }
    }

    "calculateScore should return 300 given 12 strikes (= perfect game)" {
        calculateScore("X X X X X X X X X XXX") shouldBe 300
    }

    "calculateScore should return 90 given 10 pairs of 9 and miss" {
        calculateScore("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-") shouldBe 90
    }

    "calculateScore should return 140 given 9 spares and 5" {
        calculateScore("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5-") shouldBe 140
    }

    "calculateScore should return 150 given 10 spares and 5" {
        calculateScore("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5") shouldBe 150
    }
})
