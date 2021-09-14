package minesweeper

import kotlin.random.Random


class Minesweeper1(private val rows: Int = 9, private val columns: Int = 9, private val totalMines: Int = 10) {

    private var battlefield = Array(rows) { Array(columns) { '.' } }
    private var hiddenField = Array(rows) { Array(columns) { '.' } }
    private val coordinatesOfMines = Array(totalMines) { Pair(0, 0) }
    var remainingMinesCounter = totalMines
    val arrDirections = arrayOf(
        Pair(1, 0), Pair(-1, 0),
        Pair(0, 1), Pair(0, -1),
        Pair(1, 1), Pair(-1, -1),
        Pair(1, -1), Pair(-1, 1))

    init {
        var i = 0
        val rand = Random(10)
        while (i < totalMines) {
            val (randRow, randCol) = Pair(rand.nextInt(9), rand.nextInt(9))
            if (battlefield[randRow][randCol] == 'X') continue
            //battlefield[randRow][randCol] = 'X'
            hiddenField[randRow][randCol] = 'X'
            coordinatesOfMines[i] = Pair(randRow, randCol)
            ++i
        }
        fillHints()
    }



    private fun fillHints() {
        var counter = 0

        for (row in 0 until rows) {
            for (col in 0 until columns) {
                if (hiddenField[row][col] != 'X') {
                    for ((i, j) in arrDirections) {
                        if (row + i >= rows || row + i < 0 || col + j >= columns || col + j < 0) continue
                        if (hiddenField[row + i][col + j] == 'X') counter++
                    }
                }
                if (counter != 0) {
                    hiddenField[row][col] = counter.toString()[0]
                    //battlefield[row][col] = counter.toString()[0]
                    counter = 0
                }
            }
        }
    }

    fun showBattlefield() {
        val innerField = StringBuilder("")
        val lines = Array(rows) { "" }
        for ((i, row) in (0 until rows).withIndex()) {
            for (col in 0 until columns) {
                innerField.append(battlefield[row][col])
            }
            lines[i] = innerField.toString()
            innerField.clear()
        }
        println("""
         │123456789│
        —│—————————│
        1│${lines[0]}│
        2│${lines[1]}│
        3│${lines[2]}│
        4│${lines[3]}│
        5│${lines[4]}│
        6│${lines[5]}│
        7│${lines[6]}│
        8│${lines[7]}│
        9│${lines[8]}│
        —│—————————│
        """.trimIndent())
        println("=======================")
        //showHiddenField()
    }

    private fun showHiddenField() {
        val innerField = StringBuilder("")
        val lines = Array(rows) { "" }
        for ((i, row) in (0 until rows).withIndex()) {
            for (col in 0 until columns) {
                innerField.append(hiddenField[row][col])
            }
            lines[i] = innerField.toString()
            innerField.clear()
        }
        println("""
         │123456789│
        —│—————————│
        1│${lines[0]}│
        2│${lines[1]}│
        3│${lines[2]}│
        4│${lines[3]}│
        5│${lines[4]}│
        6│${lines[5]}│
        7│${lines[6]}│
        8│${lines[7]}│
        9│${lines[8]}│
        —│—————————│
        """.trimIndent())
        println("=======================")
        println(coordinatesOfMines.joinToString())

    }

    fun tryPutMineMark(x: Int, y: Int): Boolean {
        val col = x - 1
        val row = y - 1
        return if (battlefield[row][col] != '.' && battlefield[row][col] != '*') {
            println("There is a number here or already explored!")
            false
        } else {
            battlefield[row][col] = when (battlefield[row][col]) {
                '.' -> '*'
                else -> '.'
            }

            if (hiddenField[row][col] == 'X') {
                remainingMinesCounter--
                hiddenField[row][col] = '*'
            } else if (hiddenField[row][col] == '*') {
                remainingMinesCounter++
                hiddenField[row][col] = 'X'
            }
            true
        }
    }

    fun hasWin() = remainingMinesCounter == 0

    fun tryMarkFree(x: Int, y: Int): Boolean {
        val col = x - 1
        val row = y - 1
        when {
            hiddenField[row][col] == 'X' -> {
                //если попали в Х показываем все мины и завершаем игру (возвращаем false)
                for ((y, x) in coordinatesOfMines)
                    battlefield[y][x] = 'X'
                return false

            } // если там цифра показываем её на поле и дальше идти не зачем, рядом мина
            hiddenField[row][col] != '.' -> {
                battlefield[row][col] = hiddenField[row][col]
                return true
            }
            //иначе, начинаем рекурсивно исследовать поле
            else -> {
                exploreCells(row, col)
            }
        }
        return true
    }

    private fun exploreCells(row: Int, col: Int) {
        // три условия выхода из реурсии
        // 1 - мы за границей поля
        if (row >= rows || row < 0 || col >= columns || col < 0)
            return
        //2 - поле уже исследовано
        if (hiddenField[row][col] == '/')
            return

        //исследуем данную точку
        when {
            hiddenField[row][col] != '.' -> {
                battlefield[row][col] = hiddenField[row][col]
                hiddenField[row][col] = '/'
            }
            else -> {
                hiddenField[row][col] = '/'  //вопрос: надо ли менять на скрытом поле точки? надо подумать
                battlefield[row][col] = '/'
            }
        }
        //3 - если рядом есть хоть одна мина
        if (calcMinesNear(row, col) != 0) return
        //вызываем исследование по окружающим точкам в 8 направлениях
        for ((i, j) in arrDirections) {
            exploreCells(row + i, col + j)
        }

    }

    //проверяем колличество мин вокруг позиции
    private fun calcMinesNear(row: Int, col: Int): Int {
        if (row >= rows || row < 0 || col >= columns || col < 0)
            return 0
        var counter = 0
        for ((i, j) in arrDirections) {
            if (row + i >= rows || row + i < 0 || col + j >= columns || col + j < 0) continue
            if (hiddenField[row + i][col + j] == 'X' || hiddenField[row + i][col + j] == '*') counter++
        }
        return counter
    }

}