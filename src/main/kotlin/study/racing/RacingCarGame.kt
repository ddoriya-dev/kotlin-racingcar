package study.racing

/**
 * @author 이상준
 */
class RacingCarGame(
    private val gameSettings: GameSettings,
) {
    private val carList = mutableListOf<Car>()

    private fun init() {
        gameSettings.carNameList.forEach {
            this.carList.add(Car(it))
        }
    }

    private fun playGame() {
        carList.forEach {
            it.move()
            gameProcessMessageView(it)
        }
    }

    fun start() {
        init()

        repeat(gameSettings.racingCount) {
            playGame()
            println()
        }
        gameWinnerMessageView(winnerList(this.carList))
    }

    fun winnerList(carList: List<Car>): List<Car> {
        val maxPosition = carList.maxOf { it.position }
        return carList.filter { it.position == maxPosition }
    }

    private fun gameProcessMessageView(car: Car) {
        print("${car.name} : ")
        repeat(car.position) {
            print(GameRule.RACING_CAR_MOVE_TEXT)
        }
        println()
    }

    private fun gameWinnerMessageView(winnerList: List<Car>) {
        println("${winnerList.joinToString(", ") { it.name }} ${GameRule.WINNER_MESSAGE} ")
    }
}

fun main() {
//    val gameSettings = RacingCarGameSettings().inputBySettings()
    val gameSettings = GameSettings(listOf("pobi", "crong", "honux"), 5)
    val racingCarGame = RacingCarGame(gameSettings)
    racingCarGame.start()
}
