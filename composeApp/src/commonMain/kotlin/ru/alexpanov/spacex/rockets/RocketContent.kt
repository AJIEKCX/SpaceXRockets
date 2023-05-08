package ru.alexpanov.spacex.rockets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import ru.alexpanov.rockets.api.data.RocketParamUiModel
import ru.alexpanov.rockets.api.data.RocketStageUiModel
import ru.alexpanov.rockets.api.data.RocketUiModel
import ru.alexpanov.spacex.MR
import ru.alexpanov.spacex.theme.cardPrimary
import ru.alexpanov.spacex.theme.textSecondary
import ru.alexpanov.spacex.theme.textTertiary
import ru.alexpanov.spacex.widget.AppButton
import ru.alexpanov.spacex.widget.RocketImage

@Composable
fun RocketContent(
    rocket: RocketUiModel,
    onShowLaunchesClick: () -> Unit,
    onShowSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        RocketImage(
            url = rocket.flickrImage,
            modifier = modifier
                .fillMaxWidth()
                .height(260.dp)
        )
        Column(
            modifier = Modifier
                .offset(y = ((-32).dp))
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colors.background)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 32.dp, top = 48.dp, end = 24.dp)
            ) {
                Text(
                    rocket.name,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onShowSettingsClick) {
                    Icon(painterResource(MR.images.settings), contentDescription = null)
                }
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 32.dp),
                modifier = Modifier.padding(top = 32.dp, bottom = 40.dp)
            ) {
                items(rocket.params) { param ->
                    RocketParamCard(param)
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                RocketInfoCell(
                    title = stringResource(RocketsR.strings.rocket_first_flight),
                    value = rocket.firstFlight
                )
                RocketInfoCell(
                    title = stringResource(RocketsR.strings.rocket_country),
                    value = rocket.country
                )
                RocketInfoCell(
                    title = stringResource(RocketsR.strings.rocket_cost_per_launch),
                    value = rocket.costPerLaunch
                )
            }
            RocketStageCell(
                title = stringResource(RocketsR.strings.rocket_stage_first),
                stage = rocket.firstStage,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 40.dp)
            )
            RocketStageCell(
                title = stringResource(RocketsR.strings.rocket_stage_second),
                stage = rocket.secondStage,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 40.dp)
            )
            AppButton(
                onClick = onShowLaunchesClick,
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                Text(
                    text = stringResource(RocketsR.strings.rocket_show_launches_btn),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}

@Composable
private fun RocketParamCard(
    param: RocketParamUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .size(96.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(MaterialTheme.colors.cardPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(param.value, style = MaterialTheme.typography.subtitle1)
        Text(
            param.title.localized(),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.textSecondary
        )
    }
}

@Composable
private fun RocketInfoCell(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.textTertiary,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
private fun RocketStageCell(
    title: String,
    stage: RocketStageUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.subtitle1
        )
        RocketStageInfoCell(
            title = stringResource(RocketsR.strings.rocket_stage_engines_count),
            value = stage.engines,
        )
        RocketStageInfoCell(
            title = stringResource(RocketsR.strings.rocket_stage_fuel_amount),
            value = stage.fuelAmountTons,
            unit = stringResource(RocketsR.strings.rocket_stage_fuel_amount_unit),
        )
        RocketStageInfoCell(
            title = stringResource(RocketsR.strings.rocket_stage_burn_time),
            value = stage.burnTimeSec,
            unit = stringResource(RocketsR.strings.rocket_stage_burn_time_unit),
        )
    }
}

@Composable
private fun RocketStageInfoCell(
    title: String,
    value: String,
    unit: String = "",
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.textTertiary,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = unit,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.textSecondary,
        )
    }
}