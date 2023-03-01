import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex

@Composable
fun Board(game: Game) {
    @Suppress("NAME_SHADOWING")
    val game by remember { mutableStateOf(game) }

    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {

            Box(
                Modifier.fillMaxWidth()
            ) {
                for (x in 0..2) {
                    for (y in 0..2) {
                        val currentSpace = x + y * 3
                        Card(
                            Modifier
                                .offset(x * 150.dp - 150.dp, y * 150.dp - 150.dp)
                                .padding(15.dp)
                                .align(Alignment.Center)
                                .clickable { if (!game.gameOver.value) game.turn(currentSpace) },
                            shape = CircleShape,) {
                            Image(painter = painterResource(
                                when(game.boardState[currentSpace]) {
                                    'X' -> "cross.svg"
                                    'O' -> "circle.svg"
                                    else -> "blank.svg"
                                }
                            ), contentScale = ContentScale.Inside,
                                contentDescription = "")
                        }
                    }
                }
                if (game.gameOver.value) {
                    Card(Modifier.align(Alignment.Center).zIndex(1f).background(Color.Green).fillMaxWidth()) {
                        Text(text = "${if (game.turn.value) 'X' else 'O'} is the winner!",
                            textAlign = TextAlign.Center)
                    }
                }
            }

        Row (modifier = Modifier.fillMaxWidth().padding(15.dp).offset(0.dp, 150.dp)
            ,horizontalArrangement = Arrangement.Center){
            Card(Modifier.offset((-50).dp)) { Text(if (game.turn.value) "Player X" else "Player O") }
            Button(onClick = { game.start() }) { Text("New Game") }
        }
    }
}