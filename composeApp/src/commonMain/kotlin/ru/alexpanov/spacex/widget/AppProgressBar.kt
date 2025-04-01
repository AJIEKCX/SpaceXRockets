package ru.alexpanov.spacex.widget

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
    )
}