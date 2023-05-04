package ru.alexpanov.rockets.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.core_network.api.DefaultSpaceXApi
import ru.alexpanov.rockets.api.data.RocketUiModel
import ru.alexpanov.rockets.api.data.RocketsUiState
import ru.alexpanov.rockets.internal.data.RocketRepository
import ru.alexpanov.rockets.internal.presentation.RocketsFeature

class RocketsComponent(
    componentContext: ComponentContext,
    private val dependencies: RocketsDependencies,
    private val navigateLaunches: (RocketUiModel) -> Unit,
    private val navigateSettings: () -> Unit,
) : Rockets, ComponentContext by componentContext {
    private val viewModel = instanceKeeper.getOrCreate {
        RocketsFeature(
            rocketRepository = RocketRepository(DefaultSpaceXApi(dependencies.httpClient)),
            settingsRepository = dependencies.settingsRepository
        )
    }
    override val state: AnyStateFlow<RocketsUiState> = viewModel.state

    override fun onLaunchesClick(rocketId: String) {
        val data = state.value as? RocketsUiState.Data ?: return
        val rocket = data.rockets.single { it.id == rocketId }
        navigateLaunches(rocket)
    }

    override fun onSettingsClick() {
        navigateSettings()
    }
}