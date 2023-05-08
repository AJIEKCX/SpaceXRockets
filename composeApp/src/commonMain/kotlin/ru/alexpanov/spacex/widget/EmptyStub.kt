package ru.alexpanov.spacex.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import ru.alexpanov.spacex.MR

@Composable
fun EmptyStub(
    title: String,
    modifier: Modifier = Modifier,
    button: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(MR.images.spacex_rocket),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .background(MaterialTheme.colors.onBackground, CircleShape)
                .padding(12.dp)
        )
        Text(
            title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 48.dp)
        )
        button()
    }
}