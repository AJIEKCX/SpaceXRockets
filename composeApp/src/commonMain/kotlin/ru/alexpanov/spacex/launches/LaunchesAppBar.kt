package ru.alexpanov.spacex.launches

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.alexpanov.launches.api.Launches

@Composable
fun LaunchesAppBar(
    component: Launches,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        Box(Modifier.fillMaxWidth()) {
            IconButton(onClick = component::onBackClicked) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onBackground
                )
            }
            Text(
                text = component.rocketName,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}