package converter

fun main() {
    val myUnitConverter = MyUnitConverter()

    while (true) {
        print("Enter what you want to convert (or exit): ")
        val answer = readLine()!!
        if (answer == "exit") break
        myUnitConverter.convertMeasure(answer)
    }
}

