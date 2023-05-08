package ru.alexpanov.spacex.widget

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        color = MaterialTheme.colors.onBackground,
        modifier = modifier
    )
}