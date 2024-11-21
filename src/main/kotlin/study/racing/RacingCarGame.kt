package study.racing

import study.racing.model.Car
import study.racing.model.RacingCarGameSettings
import study.racing.view.InputView
import study.racing.view.ResultView
import kotlin.random.Random

/**
 * @author 이상준
 */
class RacingCarGame(
    private val gameSettings: RacingCarGameSettings,
) {
    val cars = mutableListOf<Car>()

    init {
        this.cars.addAll(gameSettings.carNames.map { Car(it) })
    }

    private fun getRandomNumber(): Int {
        return Random.nextInt(Car.MAX_RANDOM_POSITION)
    }

    fun playGame(
        car: Car,
        moveNumber: Int,
    ) {
        car.move(moveNumber)
    }

    fun start() {
        repeat(gameSettings.racingCount) {
            cars.forEach {
                playGame(it, getRandomNumber())
                ResultView().gameProcessMessageView(it)
            }
            println()
        }
        ResultView().gameWinnerMessageView(winners())
    }

    fun winners(): List<Car> {
        val maxPosition = this.cars.maxOf { it.position }
        return this.cars.filter { it.position == maxPosition }
    }
}

fun main() {
    val gameSettings = InputView().inputBySettings()
    val racingCarGame = RacingCarGame(gameSettings)
    racingCarGame.start()
}
