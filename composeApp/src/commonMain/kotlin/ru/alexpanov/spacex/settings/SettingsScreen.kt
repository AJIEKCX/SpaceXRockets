package ru.alexpanov.spacex.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.alexpanov.core.model.DistanceUnit
import ru.alexpanov.core.model.MassUnit
import ru.alexpanov.settings.api.SettingsComponent
import ru.alexpanov.spacex.cardPrimary
import ru.alexpanov.spacex.insets.navBarsPadding
import ru.alexpanov.spacex.pagerIndicatorBackground
import ru.alexpanov.spacex.textSecondary
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
        SettingsCell(title = "Height") {
            DistanceUnitChooser(
                selected = state.height,
                onValueChanged = component::onHeightChanged
            )
        }
        SettingsCell(title = "Diameter") {
            DistanceUnitChooser(
                selected = state.diameter,
                onValueChanged = component::onDiameterChanged
            )
        }
        SettingsCell(title = "Mass") {
            MassUnitChooser(
                selected = state.mass,
                onValueChanged = component::onMassChanged
            )
        }
        SettingsCell(title = "Payload weight") {
            MassUnitChooser(
                selected = state.payloadWeight,
                onValueChanged = component::onPayloadWeightChanged
            )
        }
        Spacer(Modifier.height(56.dp).navBarsPadding())
    }
}

@Composable
private fun SettingsAppBar(
    onDismissClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxWidth()) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.body1,
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
            style = MaterialTheme.typography.body1,
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
        segments = DistanceUnit.values().toList(),
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
        segments = MassUnit.values().toList(),
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
        backgroundColor = MaterialTheme.colors.cardPrimary,
        selectedThumbColor = MaterialTheme.colors.onBackground,
        normalTextColor = MaterialTheme.colors.textSecondary,
        selectedTextColor = MaterialTheme.colors.pagerIndicatorBackground,
        content = content
    )
}