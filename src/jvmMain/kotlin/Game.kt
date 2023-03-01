import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class Game {
    val boardState = mutableStateListOf<Char>()
    val turn = mutableStateOf(true)
    val gameOver = mutableStateOf(false)

    init {
        repeat(9) { boardState += ' ' }
    }
    fun start() {
        boardState.clear()
        repeat(9) { boardState += ' ' }
        turn.value = true
        gameOver.value = false
    }

    fun turn(space: Int) {
        if (boardState[space] == ' ') {
            if (turn.value) {
                boardState[space] = 'X'
            } else {
                boardState[space] = 'O'
            }
            winCheck()
        }
    }

    private fun winCheck() {
        val c = if (turn.value) 'X' else 'O'
        val state = boardState.joinToString("")

        val horizontalCheck = state.chunked(3).any { row -> row.all { it == c } }
        val verticalCheck = listOf(
            state.filterIndexed { index, _ -> index % 3 == 0 },
            state.filterIndexed { index, _ -> index % 3 == 1 },
            state.filterIndexed { index, _ -> index % 3 == 2 }).any { col -> col.all { it == c } }
        val angleCheck = listOf("${state[0]}${state[4]}${state[8]}", "${state[2]}${state[4]}${state[6]}")
            .any { slant -> slant.all { it == c } }

        val winner = horizontalCheck || verticalCheck || angleCheck
        if (winner) {
            print("$c is the winner!")
            gameOver.value = true
        } else {
            turn.value = !turn.value
        }
        println(state)
    }

}