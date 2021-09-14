package cinema

import java.lang.IndexOutOfBoundsException
import java.util.Locale

class CinemaTheatre(val rows: Int, val seatsInRow: Int) {

    private var room = Array(rows) { Array(seatsInRow) { 'S' } }
    private val totalSeats = rows * seatsInRow
    private val rangeRow = room.indices
    private val rangeCol = room[0].indices
    private var counterPurchasedTickets = 0
    val totalIncome = calculateTotalIncome()
    private var currentIncome = 0

    //================Method 1==================================
    fun showAMenu(): Unit {
        println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit")
    }
    //==========================================================

    //================Method 2==================================
    fun showStatistics() {
        var percentage = if (counterPurchasedTickets == 0) {
            0.0
        }
        else {
            counterPurchasedTickets / totalSeats.toDouble() * 100
        }

        println()
        println("""
            Number of purchased tickets: $counterPurchasedTickets
            Percentage:${"% .2f".format(Locale("en", "US"), percentage)}%
            Current income: ${'$'}${currentIncome}
            Total income: ${'$'}${totalIncome}
        """.trimIndent())
    }
    //==========================================================

    //================Method 3==================================
    fun trySellTicket(row: Int, seat: Int): Boolean {

        try {
            room[row - 1][seat - 1]
        } catch (ex: IndexOutOfBoundsException) {
            println("\nWrong input!")
            return false
        }

        if (room[row - 1][seat - 1] == 'B') {
            println("\nThat ticket has already been purchased!")
            return false
        }

        val price = when {
            totalSeats <= 60 || row <= rows / 2 -> 10
            else -> 8
        }
        println("Ticket price: $${price}")
        room[row - 1][seat - 1] = 'B'
        currentIncome += price
        counterPurchasedTickets++
        return true
    }
    //==========================================================

    //================Method 4==================================
    private fun calculateTotalIncome(): Int {
        return if (totalSeats <= 60) {
            totalSeats * 10
        } else {
            (rows / 2) * seatsInRow * 10 + (rows - rows / 2) * seatsInRow * 8
        }
    }
    //==========================================================

    //================Method 5==================================
    fun showTheSeats() {
        print("\nCinema:\n  ")
        for (i in 1..seatsInRow)
            print("$i ")
        println()

        var k = 1
        for (i in rangeRow) {
            print("${k++} ")
            for (j in rangeCol) {
                print("${room[i][j]} ")
            }
            println()
        }
    }
    //==========================================================

}