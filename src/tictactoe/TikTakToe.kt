package tictactoe

import kotlin.math.abs

class TikTakToe() {
    private var positionsLine: String = "_________"
    private val X = 'X'
    private val O = 'O'
    private var currentSymbolIsX = false
    private val winner: Array<Array<Int>> = arrayOf(
        arrayOf(0, 1, 2),
        arrayOf(3, 4, 5),
        arrayOf(6, 7, 8),
        arrayOf(0, 3, 6),
        arrayOf(1, 4, 7),
        arrayOf(2, 5, 8),
        arrayOf(0, 4, 8),
        arrayOf(2, 4, 6)
    )

    fun showBoard(): Unit {
        println("""
        ---------
        | ${positionsLine[0]} ${positionsLine[1]} ${positionsLine[2]} |
        | ${positionsLine[3]} ${positionsLine[4]} ${positionsLine[5]} |
        | ${positionsLine[6]} ${positionsLine[7]} ${positionsLine[8]} |
        ---------
    """.trimIndent())
    }

    fun tryMove(posX: Int, posY: Int): Boolean {
        if (posX > 3 || posX < 1 || posY > 3 || posY < 1) {
            println("Coordinates should be from 1 to 3!")
            return false
        }

        val linearPos = (posX - 1) * 3 + (posY - 1)
        if (positionsLine[linearPos] != '_') {
            println("This cell is occupied! Choose another one!")
            return false
        }
        var currentSymbol = if (currentSymbolIsX) O else X
        val arr = positionsLine.toCharArray()
        arr[linearPos] = currentSymbol
        positionsLine = String(arr)
        currentSymbolIsX = !currentSymbolIsX
        return true
    }

    fun isWinner(): Pair<Boolean, String> {

        var resultOfGame = "Draw"

        for (w in winner) {
            if (positionsLine[w[0]] == positionsLine[w[1]] && positionsLine[w[1]] == positionsLine[w[2]]
                && positionsLine[w[2]] == X) {
                resultOfGame = "$X wins"
                return Pair(true, resultOfGame)
            } else if (positionsLine[w[0]] == positionsLine[w[1]] && positionsLine[w[1]] == positionsLine[w[2]]
                && positionsLine[w[2]] == O) {
                resultOfGame = "$O wins"
                return Pair(true, resultOfGame)
            }
        }

        if ('_' !in positionsLine)
            return Pair(true, resultOfGame)

        return Pair(false, resultOfGame)
    }

    fun defineGameState() : String {

        var xWins = 0
        var oWins = 0
        for (w in winner) {
            if (positionsLine[w[0]] == positionsLine[w[1]] && positionsLine[w[1]] == positionsLine[w[2]]
                    && positionsLine[w[2]] == X) {
                xWins++
            } else if (positionsLine[w[0]] == positionsLine[w[1]] && positionsLine[w[1]] == positionsLine[w[2]]
                            && positionsLine[w[2]] == O) {
                oWins++
            }
        }

        return if (xWins == 1 && oWins == 0 || xWins == 0 && oWins == 1) {
            if (xWins == 1) "X wins" else "O wins"
        } else if (xWins == 1 && oWins == 1 || xWins > 1 || oWins > 1
            || abs(positionsLine.count { it == X } - positionsLine.count { it == O } ) >= 2) {
            "Impossible"
        } else if (positionsLine.contains('_')) {
            "Game not finished"
        } else {
            "Draw"
        }

    }

    fun readCoordinatesFromConsole(): Pair<Int, Int> {
        print("Enter the coordinates: ")
        var pX = 0
        var pY = 0
        while (true) {
            try {
                val arr = readLine()!!.split(" ")
                pX = arr[0].toInt()
                pY = arr[1].toInt()
                break
            } catch (e: Exception) {
                println("You should enter numbers!")
                print("Enter the coordinates: ")
            }
        }
        return Pair(pX, pY)
    }
}