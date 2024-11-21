package study.racing

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/**
 * @author 이상준
 */
class RacingCarGameValidatorTest : StringSpec({
    "이름 중복 체크" {
        RacingCarGameValidator().isDuplicateNames(listOf("aa", "bb", "cc", "aa")) shouldBe true
        RacingCarGameValidator().isDuplicateNames(listOf("aa", "bb", "cc")) shouldBe false
    }
    "5자 이상 이름 길이 체크" {
        RacingCarGameValidator().isOverMaxNames(listOf("aaaaa", "bb", "cc", "dd", "ee")) shouldBe true
        RacingCarGameValidator().isOverMaxNames(listOf("aaaa", "bbbbbb", "cc", "dd", "ee")) shouldBe true
        RacingCarGameValidator().isOverMaxNames(listOf("aa", "bb", "cc", "dd")) shouldBe false
    }
})
