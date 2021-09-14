package parking

fun main() {
    val myParking = MyParking()
    var command = readLine()!!.split(" ")
    while (true) {
        if (command[0] == "exit") break
        when(command[0]) {
            "park" -> myParking.park(Car(command[1], command[2]))
            "leave" -> myParking.leave(command[1].toInt())
            "create" -> myParking.spots = command[1].toInt()
            "status" -> myParking.printStatus()
            else -> println("No such command. Try again!")
        }
        command = readLine()!!.split(" ")
    }
}
