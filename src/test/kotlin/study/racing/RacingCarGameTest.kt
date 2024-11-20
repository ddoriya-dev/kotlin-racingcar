package study.racing

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import study.racing.model.Car
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
    "자동차 게임 설정 Input 예외" {
        var input = "aa,bb,cc,aa\n5\n"
        var fakeInputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(fakeInputStream)

        var exception =
            shouldThrow<IllegalArgumentException> {
                InputView().inputBySettings()
            }
        exception.message should startWith("중복된 이름이 존재 합니다.")

        input = "3\nbb\n"
        fakeInputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(fakeInputStream)

        exception =
            shouldThrow<IllegalArgumentException> {
                InputView().inputBySettings()
            }
        exception.message should startWith("For input")
    }
    "이름 중복 체크" {
        RacingCarGameValidator().isDuplicateNames(listOf("aa", "bb", "cc", "aa")) shouldBe true
        RacingCarGameValidator().isDuplicateNames(listOf("aa", "bb", "cc")) shouldBe false
    }
    "5자 이상 이름 길이 체크" {
        RacingCarGameValidator().isOverMaxNames(listOf("aaaaa", "bb", "cc", "dd", "ee")) shouldBe true
        RacingCarGameValidator().isOverMaxNames(listOf("aaaa", "bbbbbb", "cc", "dd", "ee")) shouldBe true
        RacingCarGameValidator().isOverMaxNames(listOf("aa", "bb", "cc", "dd")) shouldBe false
    }
    "자동차가 이동 하지 않는다." {
        (0..3).forEach {
            Car("test").apply {
                move(it)
                position shouldBe 0
            }
        }
    }
    "자동차가 이동 한다." {
        (4..9).forEach {
            Car("test").apply {
                move(it)
                position shouldBe 1
            }
        }
    }
    "자동차 게임 승리" {
        val cars =
            listOf(
                Car("test1", 1),
                Car("test2", 2),
                Car("test3", 3),
                Car("test4", 4),
                Car("test5", 5),
            )
        val racingCarGameSettings = RacingCarGameSettings(cars.map { it.name }, cars.size)

        RacingCarGame(racingCarGameSettings).winners(cars).size shouldBe 1
        RacingCarGame(racingCarGameSettings).winners(cars)[0].name shouldBe "test5"
    }

    "자동차 게임 중복 승리" {
        val cars =
            listOf(
                Car("test1", 1),
                Car("test2", 2),
                Car("test3", 3),
                Car("test4", 5),
                Car("test5", 5),
            )
        val racingCarGameSettings = RacingCarGameSettings(cars.map { it.name }, cars.size)

        RacingCarGame(racingCarGameSettings).winners(cars).size shouldBe 2
        RacingCarGame(racingCarGameSettings).winners(cars).map { it.name } shouldBe arrayOf("test4", "test5")
    }
})
