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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.alexpanov.rockets.api.data.RocketParamUiModel
import ru.alexpanov.rockets.api.data.RocketStageUiModel
import ru.alexpanov.rockets.api.data.RocketUiModel
import ru.alexpanov.spacex.cardPrimary
import ru.alexpanov.spacex.textSecondary
import ru.alexpanov.spacex.textTertiary
import ru.alexpanov.spacex.widget.RocketImage

@Composable
fun RocketContent(
    rocket: RocketUiModel,
    onShowLaunchesClick: () -> Unit,
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
            Text(
                rocket.name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 32.dp, top = 48.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 32.dp),
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 40.dp)
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
                    title = "First flight",
                    value = rocket.firstFlight
                )
                RocketInfoCell(
                    title = "Country",
                    value = rocket.country
                )
                RocketInfoCell(
                    title = "Cost per launch",
                    value = rocket.costPerLaunch
                )
            }
            RocketStageCell(
                title = "First stage",
                stage = rocket.firstStage,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 40.dp)
            )
            RocketStageCell(
                title = "Second stage",
                stage = rocket.secondStage,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 40.dp)
            )
            Button(
                onClick = onShowLaunchesClick,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.cardPrimary
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Show launches",
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
            param.title,
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
            title = "Engines count",
            value = stage.engines,
        )
        RocketStageInfoCell(
            title = "Fuel amount",
            value = stage.fuelAmountTons,
            unit = "ton"
        )
        RocketStageInfoCell(
            title = "Burn time",
            value = stage.burnTimeSec,
            unit = "sec"
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