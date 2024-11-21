package study.racing

/**
 * @author 이상준
 */
class RacingCarGameValidator {
    fun isDuplicateNames(names: List<String>): Boolean {
        return names.size != names.toSet().size
    }

    fun isOverMaxNames(names: List<String>): Boolean {
        return names.any { it.length >= GameRule.MAX_CAR_NAME_SIZE }
    }
}
