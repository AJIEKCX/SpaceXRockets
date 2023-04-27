package ru.alexpanov.spacex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.alexpanov.launches.api.Launches
import ru.alexpanov.rockets.api.Rockets

@Composable
fun RocketsScreen(component: Rockets) {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Rockets")
        }
    }
}

@Composable
fun LaunchesScreen(component: Launches) {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Launches")
        }
    }
}