package parking

class MyParking(_spots: Int = 0) {
    var spots = _spots
        set(value) {
            println("Created a parking lot with $value spots.")
            field = value
            carStorage = Array<Car?>(value) { null }
        }
    private var carStorage = Array<Car?>(spots) { null }
    var isFull: Boolean = false

    //////////////////////////////////////////////////////////////////
    fun park(car: Car) {
        if (spots == 0) {
            println("Sorry, a parking lot has not been created.")
            return
        }
        for (i in carStorage.indices) {
            if (carStorage[i] == null) {
                println("${car.color} car parked in spot ${i + 1}.")
                carStorage[i] = car
                isFull = false
                break
            } else {
                isFull = true
            }
        }
        if (isFull) println("Sorry, the parking lot is full.")
    }

    //////////////////////////////////////////////////////////////////
    fun leave(place: Int) {
        if (spots == 0) {
            println("Sorry, a parking lot has not been created.")
            return
        }
        val index = place - 1
        if (carStorage[index] == null) {
            println("There is no car in spot $place.")
        }
        else {
            carStorage[index] = null
            println("Spot $place is free.")
        }
    }

    //////////////////////////////////////////////////////////////////
    fun printStatus() {
        if (spots == 0) {
            println("Sorry, a parking lot has not been created.")
            return
        }
        var isEmpty = true
        for (i in carStorage.indices) {
            if (carStorage[i] == null) continue
            println("${i + 1} ${carStorage[i]}")
            isEmpty = false
        }
        if (isEmpty)
            println("Parking lot is empty.")
    }


    companion object {
        fun demoStage1() {
            println("White car has parked.")
            println("Yellow car left the parking lot.")
            println("Green car just parked here.")
        }
    }
}