package ru.alexpanov.rockets.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.rockets.api.data.RocketUiModel
import ru.alexpanov.rockets.api.data.RocketsUiState
import ru.alexpanov.rockets.internal.di.createRocketsModules
import ru.alexpanov.rockets.internal.presentation.RocketsFeature
import ru.kontur.core_koin.ComponentKoinContext

class RocketsComponent(
    componentContext: ComponentContext,
    dependencies: RocketsDependencies,
    private val navigateLaunches: (RocketUiModel) -> Unit,
    private val navigateSettings: () -> Unit,
) : Rockets, ComponentContext by componentContext {
    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        createRocketsModules(dependencies)
    )

    private val feature: RocketsFeature = instanceKeeper.getOrCreate { scope.get() }

    override val state: AnyStateFlow<RocketsUiState> = feature.state

    override fun onLaunchesClick(rocketId: String) {
        val data = state.value as? RocketsUiState.Data ?: return
        val rocket = data.rockets.single { it.id == rocketId }
        navigateLaunches(rocket)
    }

    override fun onSettingsClick() {
        navigateSettings()
    }
}