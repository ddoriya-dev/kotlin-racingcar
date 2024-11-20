package study.racing

import study.racing.model.Car
import study.racing.model.RacingCarGameSettings
import study.racing.view.InputView
import study.racing.view.ResultView

/**
 * @author 이상준
 */
class RacingCarGame(
    private val gameSettings: RacingCarGameSettings,
) {
    private val cars = mutableListOf<Car>()

    private fun init() {
        this.cars.addAll(gameSettings.carNames.map { Car(it) })
    }

    private fun playGame() {
        cars.forEach {
            it.move()
            ResultView().gameProcessMessageView(it)
        }
    }

    fun start() {
        init()

        repeat(gameSettings.racingCount) {
            playGame()
            println()
        }
        ResultView().gameWinnerMessageView(winners(this.cars))
    }

    fun winners(cars: List<Car>): List<Car> {
        val maxPosition = cars.maxOf { it.position }
        return cars.filter { it.position == maxPosition }
    }
}

fun main() {
    val gameSettings = InputView().inputBySettings()
    val racingCarGame = RacingCarGame(gameSettings)
    racingCarGame.start()
}
