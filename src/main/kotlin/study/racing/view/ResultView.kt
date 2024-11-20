package study.racing.view

import study.racing.GameRule
import study.racing.model.Car

/**
 * @author 이상준
 */
class ResultView {
    fun gameProcessMessageView(car: Car) {
        print("${car.name} : ")
        repeat(car.position) {
            print(GameRule.RACING_CAR_MOVE_TEXT)
        }
        println()
    }

    fun gameWinnerMessageView(winners: List<Car>) {
        println("${winners.joinToString(", ") { it.name }} ${GameRule.WINNER_MESSAGE} ")
    }
}
