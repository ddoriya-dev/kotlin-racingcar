package study.racing

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import java.io.ByteArrayInputStream
import kotlin.random.Random

/**
 * @author 이상준
 */
class RacingCarGameTest : StringSpec({
    "자동차 게임 설정 Input 테스트" {
        val racingCarGameSettings = RacingCarGameSettings()

        val input = "aa,bb,cc\n5\n"
        val fakeInputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(fakeInputStream)

        val gameSettings: GameSettings = racingCarGameSettings.inputBySettings()

        gameSettings.carNameList.size shouldBe 3
        gameSettings.racingCount shouldBe 5

        gameSettings.carNameList[0] shouldBe "aa"
        gameSettings.carNameList[1] shouldBe "bb"
        gameSettings.carNameList[2] shouldBe "cc"
    }

    "자동차 게임 설정 Input 예외" {
        val racingCarGameSettings = RacingCarGameSettings()

        var input = "aa,bb,cc,aa\n5\n"
        var fakeInputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(fakeInputStream)

        var exception =
            shouldThrow<IllegalArgumentException> {
                racingCarGameSettings.inputBySettings()
            }
        exception.message should startWith("중복된 이름이 존재 합니다.")

        input = "3\nbb\n"
        fakeInputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(fakeInputStream)

        exception =
            shouldThrow<IllegalArgumentException> {
                racingCarGameSettings.inputBySettings()
            }
        exception.message should startWith("For input")
    }
    "자동차 게임 Move 테스트" {
        Car("test", Random(123)).apply {
            move()
            position shouldBe 1
        }

        Car("test", Random(1234)).apply {
            move()
            position shouldBe 0
        }
    }
    "자동차 게임 승리" {
        var winnerList =
            RacingCarGame(GameSettings(carNameList = listOf("test1", "test2", "test3"), racingCount = 5)).winnerList(
                listOf(
                    Car("test1", Random(123)).apply {
                        move()
                        move()
                        move()
                    },
                    Car("test2"),
                    Car("test3"),
                ),
            )
        winnerList[0].name shouldBe "test1"

        winnerList =
            RacingCarGame(GameSettings(carNameList = listOf("test1", "test2", "test3"), racingCount = 5)).winnerList(
                listOf(
                    Car("test1", Random(123)).apply {
                        move()
                        move()
                        move()
                    },
                    Car("test2", Random(123)).apply {
                        move()
                        move()
                        move()
                    },
                    Car("test3"),
                ),
            )
        winnerList[0].name shouldBe "test1"
        winnerList[1].name shouldBe "test2"
    }
})
