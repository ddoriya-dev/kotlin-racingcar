package study.racing

/**
 * @author 이상준
 */
open class RacingCarGameValidator {
    fun isDuplicateName(nameList: List<String>): Boolean {
        val nameSet = mutableSetOf<String>()
        nameList.forEach {
            if (!nameSet.add(it)) {
                return false
            }
        }

        return true
    }
}
