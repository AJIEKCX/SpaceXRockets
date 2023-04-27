package ru.alexpanov.spacex

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColors(background = Color.Black)

@Composable
internal fun AppTheme(content: @Composable () -> Unit) {
    val colors = DarkColors

    MaterialTheme(
        colors = colors,
        content = {
            Surface(content = content, color = MaterialTheme.colors.background)
        }
    )
}
