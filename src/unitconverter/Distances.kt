package converter

enum class Distances(val meters: Double, val patterns: Triple<String, String, String>) {
    METER(1.0, Triple("m", "meter", "meters")),
    KILOMETER(1000.0, Triple("km", "kilometer", "kilometers")),
    CENTIMETER(0.01, Triple("cm", "centimeter", "centimeters")),
    MILLIMETER(0.001, Triple("mm", "millimeter", "millimeters")),
    MILE(1609.35, Triple("mi", "mile", "miles")),
    YARD(0.9144, Triple("yd", "yard", "yards")),
    FOOT(0.3048, Triple("ft", "foot", "feet")),
    INCH(0.0254, Triple("in", "inch", "inches")),
    NULL(.0, Triple("", "", "???"));

    companion object {
        fun findDistance(pattern: String): Distances {
            for (enum in values()) {
                when (pattern) {
                    enum.patterns.first, enum.patterns.third, enum.patterns.second -> return enum
                }
            }
            return NULL
        }

        fun isDistance(unit: String): Boolean {
            val dist = findDistance(unit)
            return dist.patterns.second != ""
        }
    }
}