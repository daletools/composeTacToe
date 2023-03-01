import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    MaterialTheme {
        val game = remember { Game() }
        Board(game)
    }
}

fun main() = application {
    val windowSize = WindowState(size = DpSize(600.dp, 600.dp))
    Window(
        onCloseRequest = ::exitApplication,
        state = windowSize
        ) {
        App()
    }
}
