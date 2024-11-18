package study.racing

import kotlin.random.Random

/**
 * @author 이상준
 */
class Car(
    val name: String,
    private val random: Random = Random.Default,
) {
    var position: Int = 0
        private set

    fun move() {
        if (random.nextInt(GameRule.MIN_RANDOM_POSITION, GameRule.MAX_RANDOM_POSITION) > 3) {
            this.position += 1
        }
    }
}
