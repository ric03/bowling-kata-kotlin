import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class MainKtTest : StringSpec({

    "calculateSum should return integer" {
        calculateScore("").shouldBeTypeOf<Int>()
    }

    "calculateScore should return 300 given 12 strikes (= perfect game)" {
        calculateScore("X X X X X X X X X X X X") shouldBe 300
    }

    "calculateScore should return 90 given 10 pairs of 9 and miss" {
        calculateScore("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-") shouldBe 90
    }

    "calculateScore should return 90 given 10 pairs of 5 and spare" {
        calculateScore("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5") shouldBe 150
    }
})
