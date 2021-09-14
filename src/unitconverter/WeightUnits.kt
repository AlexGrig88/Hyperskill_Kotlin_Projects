package converter


enum class WeightUnits(val grams: Double, val symbol: String, val singular: String, val plural: String) {
    GRAM(1.0, "g", "gram", "grams"),
    KILOGRAM(1000.0, "kg", "kilogram", "kilograms"),
    MILLIGRAM(0.001, "mg", "milligram", "milligrams"),
    POUND(453.592, "lb", "pound", "pounds"),
    OUNCE(28.3495, "oz", "ounce", "ounces"),
    NULL(.0, "", "", "???");

    companion object {
        fun findWeight(pattern: String): WeightUnits {
            for (enum in WeightUnits.values()) {
                when (pattern) {
                    enum.symbol, enum.singular, enum.plural -> return enum
                }
            }
            return NULL
        }

        fun isWeight(unit: String): Boolean {
            val w = findWeight(unit)
            return w.singular != ""
        }
    }
}