import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.alexpanov.rockets.api.Rockets
import ru.alexpanov.rockets.api.data.RocketUiModel
import ru.alexpanov.rockets.api.data.RocketsUiState
import ru.alexpanov.spacex.rockets.RocketContent
import ru.alexpanov.spacex.theme.pagerIndicatorBackground
import ru.alexpanov.spacex.widget.AppProgressBar
import ru.alexpanov.spacex.widget.ErrorStub
import ru.alexpanov.spacex.widget.HorizontalPagerIndicator

@Composable
fun RocketsScreen(component: Rockets) {
    val uiState by component.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {
            is RocketsUiState.Loading -> {
                AppProgressBar()
            }

            is RocketsUiState.Data -> {
                RocketsContent(
                    rockets = state.rockets,
                    onLaunchesClick = component::onLaunchesClick,
                    onSettingsClick = component::onSettingsClick
                )
            }

            is RocketsUiState.Error -> {
                ErrorStub(onClick = component::onTryAgainClick)
            }
        }
    }
}

@Composable
private fun RocketsContent(
    rockets: List<RocketUiModel>,
    onLaunchesClick: (rocketId: String) -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(0) { rockets.size }
        HorizontalPager(
            state = pagerState,
            key = { index -> rockets[index].id },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            val rocket = rockets[page]
            RocketContent(
                rocket = rocket,
                onShowLaunchesClick = { onLaunchesClick(rocket.id) },
                onShowSettingsClick = onSettingsClick
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.pagerIndicatorBackground)
                .systemBarsPadding(),
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = rockets.size,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 24.dp)
            )
        }
    }
}