package ru.alexpanov.spacex.launches

import androidx.compose.foundation.Image
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource
import ru.alexpanov.launches.api.Launches
import ru.alexpanov.launches.api.data.LaunchUiModel
import ru.alexpanov.launches.api.data.LaunchesUiState
import ru.alexpanov.launches.internal.domain.model.LaunchStatus
import ru.alexpanov.spacex.MR
import ru.alexpanov.spacex.theme.textSecondary

@Composable
fun LaunchesScreen(
    component: Launches,
    topBarContent: @Composable (component: Launches) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val uiState by component.state.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = { topBarContent(component) }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
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
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    launch.name,
                    fontSize = 20.sp,
                    maxLines = 1,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    launch.launchDate,
                    maxLines = 1,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.textSecondary
                )
            }
            Image(
                painterResource(launch.status.iconRes),
                contentDescription = null
            )
        }
    }
}

private val LaunchStatus.iconRes: ImageResource
    get() = when (this) {
        LaunchStatus.Success -> MR.images.launch_success
        LaunchStatus.Error -> MR.images.launch_failure
        LaunchStatus.Unknown -> MR.images.launch_unknown
    }
