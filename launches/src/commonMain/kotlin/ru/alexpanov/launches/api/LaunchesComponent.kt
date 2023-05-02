package ru.alexpanov.launches.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.core_network.api.DefaultSpaceXApi
import ru.alexpanov.launches.api.data.LaunchesUiState
import ru.alexpanov.launches.internal.data.LaunchesRepository
import ru.alexpanov.launches.internal.presentation.LaunchesFeature

class LaunchesComponent(
    override val rocketName: String,
    private val rocketId: String,
    private val dependencies: LaunchesDependencies,
    componentContext: ComponentContext
) : Launches, ComponentContext by componentContext {
    private val viewModel = instanceKeeper.getOrCreate {
        LaunchesFeature(
            rocketId = rocketId,
            launchesRepository = LaunchesRepository(DefaultSpaceXApi(dependencies.httpClient)),
        )
    }

    override val state: AnyStateFlow<LaunchesUiState> = viewModel.state
}