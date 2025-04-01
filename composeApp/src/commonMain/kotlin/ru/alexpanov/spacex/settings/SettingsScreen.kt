package ru.alexpanov.spacex.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import ru.alexpanov.core.model.DistanceUnit
import ru.alexpanov.core.model.MassUnit
import ru.alexpanov.settings.api.SettingsComponent
import ru.alexpanov.spacex.theme.cardPrimary
import ru.alexpanov.spacex.theme.pagerIndicatorBackground
import ru.alexpanov.spacex.theme.textSecondary
import ru.alexpanov.spacex.widget.SegmentedControl

@Composable
fun SettingsScreen(
    component: SettingsComponent,
    modifier: Modifier = Modifier
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SettingsAppBar(
            onDismissClick = component::onDismissClick,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        SettingsCell(title = stringResource(SettingsR.strings.settings_height)) {
            DistanceUnitChooser(
                selected = state.height,
                onValueChanged = component::onHeightChanged
            )
        }
        SettingsCell(title = stringResource(SettingsR.strings.settings_diameter)) {
            DistanceUnitChooser(
                selected = state.diameter,
                onValueChanged = component::onDiameterChanged
            )
        }
        SettingsCell(title = stringResource(SettingsR.strings.settings_mass)) {
            MassUnitChooser(
                selected = state.mass,
                onValueChanged = component::onMassChanged
            )
        }
        SettingsCell(title = stringResource(SettingsR.strings.settings_payload_weight)) {
            MassUnitChooser(
                selected = state.payloadWeight,
                onValueChanged = component::onPayloadWeightChanged
            )
        }
        Spacer(Modifier.height(56.dp).systemBarsPadding())
    }
}

@Composable
private fun SettingsAppBar(
    onDismissClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxWidth()) {
        Text(
            text = stringResource(SettingsR.strings.settings_title),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(Alignment.Center)
        )
        IconButton(
            onClick = onDismissClick,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterEnd)
        ) {
            Icon(Icons.Default.Close, contentDescription = null)
        }
    }
}

@Composable
private fun SettingsCell(
    title: String,
    modifier: Modifier = Modifier,
    chooser: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        chooser()
    }
}

@Composable
private fun DistanceUnitChooser(
    selected: DistanceUnit,
    onValueChanged: (DistanceUnit) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsSegmentedControl(
        segments = DistanceUnit.entries,
        selected = selected,
        onSegmentSelected = onValueChanged,
        modifier = modifier
    ) {
        Text(it.value)
    }
}

@Composable
private fun MassUnitChooser(
    selected: MassUnit,
    onValueChanged: (MassUnit) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsSegmentedControl(
        segments = MassUnit.entries,
        selected = selected,
        onSegmentSelected = onValueChanged,
        modifier = modifier
    ) {
        Text(it.value)
    }
}

@Composable
private fun <T : Any> SettingsSegmentedControl(
    segments: List<T>,
    selected: T,
    onSegmentSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit
) {
    SegmentedControl(
        segments = segments,
        selectedSegment = selected,
        onSegmentSelected = onSegmentSelected,
        modifier = modifier.width(115.dp),
        backgroundColor = MaterialTheme.colorScheme.cardPrimary,
        selectedThumbColor = MaterialTheme.colorScheme.onBackground,
        normalTextColor = MaterialTheme.colorScheme.textSecondary,
        selectedTextColor = MaterialTheme.colorScheme.pagerIndicatorBackground,
        content = content
    )
}