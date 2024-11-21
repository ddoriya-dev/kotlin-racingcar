package study.racing.model

/**
 * @author 이상준
 */
class Car(
    val name: String,
    position: Int = DEFAULT_POSITION,
) {
    var position: Int = position
        private set

    fun move(moveNumber: Int) {
        if (moveNumber < 0 || moveNumber > MAX_RANDOM_POSITION) throw IllegalArgumentException(MOVE_ERROR_MESSAGE)
        if (moveNumber >= FORWARD_NUMBER) position++
    }

    companion object {
        const val FORWARD_NUMBER = 4
        const val DEFAULT_POSITION = 0
        const val MAX_RANDOM_POSITION = 9
        const val MOVE_ERROR_MESSAGE = "음수 또는 10 이상의 숫자는 입력할 수 없습니다."
    }
}
