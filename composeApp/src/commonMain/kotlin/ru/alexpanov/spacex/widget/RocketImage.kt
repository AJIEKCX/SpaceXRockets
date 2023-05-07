package ru.alexpanov.spacex.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun RocketImage(
    url: String,
    modifier: Modifier
)