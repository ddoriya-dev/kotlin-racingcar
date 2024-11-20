package study.racing.view

import study.racing.RacingCarGameValidator
import study.racing.model.RacingCarGameSettings

/**
 * @author 이상준
 */
class InputView : RacingCarGameValidator() {
    fun inputBySettings(): RacingCarGameSettings {
        println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).")
        val carNames = readlnOrNull()?.split(",") ?: throw IllegalArgumentException()

        if (isDuplicateNames(carNames)) throw IllegalArgumentException("중복된 이름이 존재 합니다.")
        if (isOverMaxNames(carNames)) throw IllegalArgumentException("자동차 이름이 5자를 초과합니다.")

        println("시도할 횟수는 몇 회인가요?")
        val racingCount = readlnOrNull()?.toInt() ?: throw NumberFormatException()

        return RacingCarGameSettings(carNames, racingCount)
    }
}
