package study.racing

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import study.racing.model.RacingCarGameSettings
import study.racing.view.InputView
import java.io.ByteArrayInputStream

/**
 * @author 이상준
 */
class RacingCarGameTest : StringSpec({
    "자동차 게임 설정 Input 테스트" {
        val input = "aa,bb,cc\n5\n"
        val fakeInputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(fakeInputStream)

        val racingCarGameSettings: RacingCarGameSettings = InputView().inputBySettings()

        racingCarGameSettings.carNames.size shouldBe 3
        racingCarGameSettings.racingCount shouldBe 5

        racingCarGameSettings.carNames[0] shouldBe "aa"
        racingCarGameSettings.carNames[1] shouldBe "bb"
        racingCarGameSettings.carNames[2] shouldBe "cc"
    }
    "자동차 게임 설정 이름이 없을 경우 예외 발생" {
        var input = "\n5\n"
        var fakeInputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(fakeInputStream)

        var exception =
            shouldThrow<IllegalArgumentException> {
                InputView().inputBySettings()
            }
        exception.message should startWith("입력이 없습니다.")
    }
    "자동차 게임 설정 레이싱 횟수가 없을 경우 예외 발생" {
        var input = "aa,bb\n\n"
        var fakeInputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(fakeInputStream)

        var exception =
            shouldThrow<IllegalArgumentException> {
                InputView().inputBySettings()
            }
        exception.message should startWith("For input string")
    }
    "자동차 게임 설정 중복 이름 예외 발생" {
        var input = "aa,bb,cc,aa\n5\n"
        var fakeInputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(fakeInputStream)

        var exception =
            shouldThrow<IllegalArgumentException> {
                InputView().inputBySettings()
            }
        exception.message should startWith("중복된 이름이 존재 합니다.")
    }
    "자동차 게임 승리 테스트" {
        val carNames = listOf("test1", "test2", "test3", "test4", "test5")
        val racingCarGameSettings = RacingCarGameSettings(carNames, carNames.size)

        val racingCarGame = RacingCarGame(racingCarGameSettings)
        racingCarGame.playGame(racingCarGame.cars[0], 5)

        racingCarGame.winners()[0].name shouldBe "test1"
    }
    "자동차 게임 중복 우승자 테스트" {
        val carNames = listOf("test1", "test2", "test3", "test4", "test5")
        val racingCarGameSettings = RacingCarGameSettings(carNames, carNames.size)

        val racingCarGame = RacingCarGame(racingCarGameSettings)
        racingCarGame.playGame(racingCarGame.cars[0], 5)
        racingCarGame.playGame(racingCarGame.cars[1], 5)

        racingCarGame.winners().size shouldBe 2
        racingCarGame.winners().map { it.name } shouldBe arrayOf("test1", "test2")
    }
})
