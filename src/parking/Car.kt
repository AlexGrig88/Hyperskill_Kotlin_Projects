package parking

data class Car(val regNumber: String = "", val color: String = "") {
    override fun toString(): String {
        return "$regNumber $color"
    }
}