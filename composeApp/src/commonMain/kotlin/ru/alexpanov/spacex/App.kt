package ru.alexpanov.spacex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.alexpanov.launches.api.Launches
import ru.alexpanov.rockets.api.Rockets
import ru.alexpanov.rockets.api.data.RocketsUiState

@Composable
fun RocketsScreen(component: Rockets) {
    val state by component.state.collectAsState()

    Box(Modifier.fillMaxSize()) {
        when (state) {
            is RocketsUiState.Data -> {
                Text("Data")
            }
            RocketsUiState.Error -> {
                Text("Error")
            }
            RocketsUiState.Loading -> CircularProgressIndicator()
        }
    }
}

@Composable
fun LaunchesScreen(component: Launches) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Launches")
    }
}