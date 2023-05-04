import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.alexpanov.rockets.api.Rockets
import ru.alexpanov.rockets.api.data.RocketsUiState
import ru.alexpanov.spacex.AppTheme

@Composable
fun RocketsScreen(component: Rockets) {
    val uiState by component.state.collectAsState()

    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is RocketsUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is RocketsUiState.Data -> {
                    Column {
                        Text(text = "Rockets")
                        Button(onClick = {
                            component.onLaunchesClick(state.rockets.first().id)
                        }) {
                            Text("Show launches")
                        }
                    }
                }
                is RocketsUiState.Error -> {
                    Text("Error")
                }
            }
        }
    }
}