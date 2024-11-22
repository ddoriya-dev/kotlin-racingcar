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
    private fun getRandomNumber(): Int {
        return Random.nextInt(Car.MAX_RANDOM_POSITION)
    }

    private fun playGame(car: Car) {
        car.move(getRandomNumber())
    }

    fun start() {
        repeat(gameSettings.racingCount) {
            gameSettings.cars.forEach {
                playGame(it)
                ResultView().gameProcessMessageView(it)
            }
            println()
        }
        ResultView().gameWinnerMessageView(winners())
    }

    fun winners(): List<Car> {
        val maxPosition = gameSettings.cars.maxOf { it.position }
        return gameSettings.cars.filter { it.position == maxPosition }
    }
}

fun main() {
    val gameSettings = InputView().inputBySettings()
    val racingCarGame = RacingCarGame(gameSettings)
    racingCarGame.start()
}
