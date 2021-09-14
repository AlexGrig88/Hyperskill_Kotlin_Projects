package converter

class MyUnitConverter {

    fun convertMeasure(inputText: String) {
        val (numAsString, unitFrom, word, unitTo) = parseInputText(inputText)

        val number = try {
            numAsString.toDouble()
        } catch (ex: NumberFormatException) {
            println("Parse error\n")
            return
        }


        val resultTriple = if (Distances.isDistance(unitFrom) && Distances.isDistance(unitTo)) {
            if (number < 0) {
                println("Length shouldn't be negative\n")
                return
            }
            convertMeasureOfLength(number, unitFrom, unitTo)
        } else if (WeightUnits.isWeight(unitFrom) && WeightUnits.isWeight(unitTo)) {
            if (number < 0) {
                println("Weight shouldn't be negative")
                return
            }
            convertMeasureOfWeight(number, unitFrom, unitTo)
        } else if (TempUnits.isTemp(unitFrom) && TempUnits.isTemp(unitTo)) {
            convertMeasureOfTemp(number, unitFrom, unitTo)
        } else {
            val res = convertFromImpossible(unitFrom, unitTo)
            println("Conversion from ${res.first} to ${res.second} is impossible\n")
            return
        }

        println("$number ${resultTriple.first} is ${resultTriple.second} ${resultTriple.third}\n")

    }

    private fun parseInputText(inputText: String) : Array<String> {
        val arr = inputText.split(" ").map { it.toLowerCase() }.toTypedArray()

        return when (arr.size) {
            4 -> arr
            5 -> {
                if (arr[1] == "degree" || arr[1] == "degrees") {
                    arrayOf(arr[0], arr[1] + " " + arr[2], arr[3], arr[4])
                } else if (arr[3] == "degree" || arr[3] == "degrees") {
                    arrayOf(arr[0], arr[1], arr[2], arr[3] + " " + arr[4])
                } else {
                    arrayOf("notNumber","","","")  //Exception will be thrown in the called function
                }
            }
            6 -> {
                if (arr[1] == "degree" || arr[1] == "degrees" && arr[4] == "degree" || arr[4] == "degrees")
                    arrayOf(arr[0], arr[1] + " " + arr[2], arr[3], arr[4] + " " + arr[5])
                else
                    arrayOf("notNumber","","","")  //Exception will be thrown in the called function
            }
            else -> arrayOf("notNumber","","","")  //Exception will be thrown in the called function
        }


    }

    private fun convertMeasureOfTemp(number: Double, unitFrom: String, unitTo: String): Triple<String, Double, String> {
        val from = TempUnits.findTemp(unitFrom)
        val to = TempUnits.findTemp(unitTo)

        val result = when {
            from == TempUnits.CELSIUS && to == TempUnits.FAHRENHEIT -> {
                number * 9 / 5 + 32.0
            }
            from == TempUnits.FAHRENHEIT && to == TempUnits.CELSIUS -> {
                (number - 32.0) * 5 / 9
            }
            from == TempUnits.CELSIUS && to == TempUnits.KELVINS -> {
                number + 273.15
            }
            from == TempUnits.KELVINS && to == TempUnits.CELSIUS -> {
                number - 273.15
            }
            from == TempUnits.KELVINS && to == TempUnits.FAHRENHEIT -> {
                number * 9 / 5 - 459.67
            }
            from == TempUnits.FAHRENHEIT && to == TempUnits.KELVINS -> {
                (number + 459.67) * 5 / 9
            }
            from == to -> number
            else -> 0.0
        }

        val unitInputForDisplay = if (number == 1.0) from.singular else from.plural
        val resUnitForDisplay = if (result == 1.0) to.singular else to.plural

        return Triple(unitInputForDisplay, result, resUnitForDisplay);
    }


    private fun convertMeasureOfWeight(number: Double, unitFrom: String, unitTo: String): Triple<String, Double, String> {

        val weightFrom : WeightUnits = WeightUnits.findWeight(unitFrom)
        val weightTo : WeightUnits = WeightUnits.findWeight(unitTo)

        val unitInputForDisplay = if (number == 1.0) weightFrom.singular else weightFrom.plural
        val result = (1 / weightTo.grams) * weightFrom.grams * number
        val resUnitForDisplay = if (result == 1.0) weightTo.singular else weightTo.plural

        return Triple(unitInputForDisplay, result, resUnitForDisplay)

    }

    private fun convertMeasureOfLength(number: Double, unitFrom: String, unitTo: String): Triple<String, Double, String>  {

        val distFrom: Distances = Distances.findDistance(unitFrom)
        val distTo: Distances = Distances.findDistance(unitTo)

        val unitInputForDisplay = if (number == 1.0) distFrom.patterns.second else distFrom.patterns.third
        val result = (1 / distTo.meters) * distFrom.meters * number
        val resUnitForDisplay = if (result == 1.0) distTo.patterns.second else distTo.patterns.third

        return Triple(unitInputForDisplay, result, resUnitForDisplay)

    }

    private fun convertFromImpossible(unitFrom: String, unitTo: String): Pair<String, String> {
        val first = when {
            Distances.isDistance(unitFrom) -> Distances.findDistance(unitFrom).patterns.third
            WeightUnits.isWeight(unitFrom) -> WeightUnits.findWeight(unitFrom).plural
            TempUnits.isTemp(unitFrom) -> TempUnits.findTemp(unitFrom).plural
            else -> "???"
        }
        val second = when {
            Distances.isDistance(unitTo) -> Distances.findDistance(unitTo).patterns.third
            WeightUnits.isWeight(unitTo) -> WeightUnits.findWeight(unitTo).plural
            TempUnits.isTemp(unitTo) -> TempUnits.findTemp(unitTo).plural
            else -> "???"
        }

        return Pair(first, second)
    }

}