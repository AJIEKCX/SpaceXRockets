package ru.alexpanov.spacex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexpanov.launches.api.Launches
import ru.alexpanov.launches.api.data.LaunchUiModel
import ru.alexpanov.launches.api.data.LaunchesUiState

@Composable
fun LaunchesScreen(component: Launches) {
    val uiState by component.state.collectAsState()

    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is LaunchesUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is LaunchesUiState.Data -> {
                    LaunchesContent(state.launches)
                }
                is LaunchesUiState.Error -> {
                    Text("Error")
                }
            }
        }
    }
}

@Composable
private fun LaunchesContent(
    launches: List<LaunchUiModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 40.dp)
    ) {
        items(launches) {
            LaunchCard(it)
        }
    }
}

@Composable
private fun LaunchCard(
    launch: LaunchUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(Modifier.padding(24.dp)) {
            Column {
                Text(launch.name, fontSize = 20.sp)
                Text(launch.launchDate, fontSize = 16.sp)
            }
        }
    }
}