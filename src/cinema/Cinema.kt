package cinema

fun main() {
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seats = readLine()!!.toInt()
    val theatre = CinemaTheatre(rows, seats)

    //locale function
    fun buyATicket() {
        do {
            println("\nEnter a row number:")
            val aRow = readLine()!!.toInt()
            println("Enter a seat number in that row:")
            val aSeat = readLine()!!.toInt()
        } while (!theatre.trySellTicket(aRow, aSeat))
    }

    do {
        var isExit = false
        theatre.showAMenu()
        var choice = readLine()!!.toInt()
        when (choice) {
            1 -> theatre.showTheSeats()
            2 -> buyATicket()
            3 -> theatre.showStatistics()
            0 -> isExit = true
            else -> println("No such item! Try again. ")
        }
    } while (!isExit)

}
