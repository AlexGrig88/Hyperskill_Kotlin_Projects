package tictactoe


fun main() {

    val tikTakToe = TikTakToe()

    tikTakToe.showBoard()

    do {
        var (posX, posY) = tikTakToe.readCoordinatesFromConsole()
        while (!tikTakToe.tryMove(posX, posY)) {
            val (pX, pY) = tikTakToe.readCoordinatesFromConsole()
            posX = pX
            posY = pY
        }
        tikTakToe.showBoard()

        if (tikTakToe.isWinner().first) {
            println(tikTakToe.isWinner().second)
            break;
        }

    } while (true)

}
