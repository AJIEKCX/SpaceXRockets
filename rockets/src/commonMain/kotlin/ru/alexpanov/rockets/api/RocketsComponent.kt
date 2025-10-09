package ru.alexpanov.rockets.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.rockets.api.data.RocketUiModel
import ru.alexpanov.rockets.api.data.RocketsUiState
import ru.alexpanov.rockets.internal.di.RocketsModule
import ru.alexpanov.rockets.internal.presentation.RocketsFeature

class RocketsComponent(
    componentContext: ComponentContext,
    dependencies: RocketsDependencies,
    private val navigateLaunches: (RocketUiModel) -> Unit,
    private val navigateSettings: () -> Unit,
) : Rockets, ComponentContext by componentContext {

    private val feature: RocketsFeature = instanceKeeper.getOrCreate {
        val module = RocketsModule(dependencies)
        module.rocketsFeature
    }

    override val state: AnyStateFlow<RocketsUiState> = feature.state

    override fun onLaunchesClick(rocketId: String) {
        val data = state.value as? RocketsUiState.Data ?: return
        val rocket = data.rockets.single { it.id == rocketId }
        navigateLaunches(rocket)
    }

    override fun onSettingsClick() {
        navigateSettings()
    }

    override fun onTryAgainClick() {
        feature.onTryAgainClick()
    }
}