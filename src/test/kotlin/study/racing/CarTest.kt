package study.racing

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import study.racing.model.Car

/**
 * @author 이상준
 */
class CarTest : StringSpec({
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
    "자동차 Move 음수값이 들어오면 예외 발생" {
        val car = Car("test")
        val exception =
            shouldThrow<IllegalArgumentException> {
                car.move(-1)
            }
        exception.message should startWith("음수 또는 10 이상의 숫자는 입력할 수 없습니다.")
    }
    "자동차 Move 10 이상 들어오면 예외 발생" {
        val car = Car("test")
        val exception =
            shouldThrow<IllegalArgumentException> {
                car.move(10)
            }
        exception.message should startWith("음수 또는 10 이상의 숫자는 입력할 수 없습니다.")
    }
})
