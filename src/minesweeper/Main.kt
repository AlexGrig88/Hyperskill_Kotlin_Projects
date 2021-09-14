package minesweeper

fun main() {
    print("How many mines do you want on the field? ")
    val quantityOfMines = readLine()!!.toInt()
    val minesweeper = Minesweeper1(totalMines = quantityOfMines)
    minesweeper.showBattlefield()

    loop@while (true) {
        print("Set/unset mines marks or claim a cell as free: ")
        val (xStr, yStr, guess) =  readLine()!!.split(" ")
        val x = xStr.toInt()
        val y = yStr.toInt()
        val isContinueGame: Boolean = when (guess) {
            "mine" -> {
                if (!minesweeper.tryPutMineMark(x, y))
                    continue@loop
                else
                    true
            }
            "free" -> {
                minesweeper.tryMarkFree(x, y)
            }
            else -> {
                println("Not such command. Try again!")
                continue@loop
            }
        }

        minesweeper.showBattlefield()

        if (!isContinueGame) {
            println("You stepped on a mine and failed!")
            break
        }
        if (minesweeper.hasWin()) {
            println("Congratulations! You found all the mines!")
            break
        }
    }
}