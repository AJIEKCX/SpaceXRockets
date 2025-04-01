package ru.alexpanov.spacex.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import ru.alexpanov.spacex.MR

@Composable
fun ErrorStub(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    EmptyStub(
        title = stringResource(MR.strings.error_stub_text),
        modifier = modifier
    ) {
        AppButton(
            onClick = onClick,
            modifier = Modifier.padding(48.dp)
        ) {
            Text(stringResource(MR.strings.error_stub_repeat_btn))
        }
    }
}