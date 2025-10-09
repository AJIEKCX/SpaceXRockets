package ru.alexpanov.launches.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.launches.api.data.LaunchesUiState
import ru.alexpanov.launches.internal.di.LaunchesModule
import ru.alexpanov.launches.internal.presentation.LaunchesFeature

class LaunchesComponent(
    override val rocketName: String,
    private val rocketId: String,
    private val navigateBack: () -> Unit,
    dependencies: LaunchesDependencies,
    componentContext: ComponentContext
) : Launches, ComponentContext by componentContext {

    private val feature: LaunchesFeature = instanceKeeper.getOrCreate {
        val module = LaunchesModule(dependencies)
        module.launchesFeature(rocketId)
    }

    override val state: AnyStateFlow<LaunchesUiState> = feature.state

    override fun onBackClicked() {
        navigateBack()
    }

    override fun onTryAgainClick() {
        feature.onTryAgainClick()
    }
}