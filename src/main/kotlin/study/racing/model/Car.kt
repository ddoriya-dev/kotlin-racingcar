package study.racing.model

import kotlin.random.Random

/**
 * @author 이상준
 */
class Car(
    val name: String,
    position: Int = DEFAULT_POSITION,
) {
    var position: Int = position
        private set

    fun move() {
        move(getRandomNumber())
    }

    fun move(moveNumber: Int) {
        if (moveNumber >= FORWARD_NUMBER) position++
    }

    private fun getRandomNumber(): Int {
        return Random.nextInt(MAX_RANDOM_POSITION)
    }

    companion object {
        const val FORWARD_NUMBER = 4
        const val DEFAULT_POSITION = 0
        const val MAX_RANDOM_POSITION = 9
    }
}
