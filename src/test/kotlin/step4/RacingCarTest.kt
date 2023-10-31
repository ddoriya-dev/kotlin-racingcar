package step4

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import step4.domain.FixedStrategy
import step4.domain.RacingCar

class RacingCarTest {
    @Test
    fun `RacingCar의 카운트가 4 이상이면 움직이는데 성공한다`() {
        val racingCar = RacingCar("name", 1)
        racingCar.tryMoving(FixedStrategy(4))
        Assertions.assertThat(racingCar.moveCount).isEqualTo(2)
    }

    @Test
    fun `RacingCar의 카운트가 3 이하이면 움직이는데 실패한다`() {
        val racingCar = RacingCar("name", 1)
        racingCar.tryMoving(FixedStrategy(2))
        Assertions.assertThat(racingCar.moveCount).isEqualTo(1)
    }
}