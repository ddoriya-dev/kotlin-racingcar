package study.racing

/**
 * @author 이상준
 */
class RacingCarGameSettings : RacingCarGameValidator() {
    fun inputBySettings(): GameSettings {
        println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).")
        val carNameList = readlnOrNull()?.split(",") ?: throw IllegalArgumentException()

        if (!isDuplicateName(carNameList)) {
            throw IllegalArgumentException("중복된 이름이 존재 합니다.")
        }

        println("시도할 횟수는 몇 회인가요?")
        val racingCount = readlnOrNull()?.toInt() ?: throw NumberFormatException()

        return GameSettings(carNameList, racingCount)
    }
}

data class GameSettings(
    var carNameList: List<String>,
    var racingCount: Int,
)
