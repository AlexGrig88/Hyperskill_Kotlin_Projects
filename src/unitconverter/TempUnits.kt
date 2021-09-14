package converter

enum class TempUnits(val options: List<String>, val singular: String, val plural: String) {
    CELSIUS(listOf("degree celsius", "degrees celsius", "celsius", "dc", "c"),
        "degree Celsius", "degrees Celsius"),
    FAHRENHEIT(listOf("degree fahrenheit", "degrees fahrenheit", "fahrenheit", "df", "f"),
        "degree Fahrenheit", "degrees Fahrenheit"),
    KELVINS(listOf("kelvin", "kelvins", "k"), "kelvin", "kelvins"),
    NULL(emptyList(), "???", "???");

    companion object {
        fun findTemp(pattern: String): TempUnits {
            for (enum in values()) {
                when (pattern) {
                    in enum.options -> return enum
                }
            }
            return NULL
        }

        fun isTemp(unit: String): Boolean {
            val temp = findTemp(unit)
            return temp.options.isNotEmpty()
        }

    }
}