package ru.alexpanov.launches.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.koin.core.parameter.parametersOf
import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.launches.api.data.LaunchesUiState
import ru.alexpanov.launches.internal.di.createLaunchesModules
import ru.alexpanov.launches.internal.presentation.LaunchesFeature
import ru.kontur.core_koin.ComponentKoinContext

class LaunchesComponent(
    override val rocketName: String,
    private val rocketId: String,
    private val navigateBack: () -> Unit,
    dependencies: LaunchesDependencies,
    componentContext: ComponentContext
) : Launches, ComponentContext by componentContext {
    private val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    private val scope = koinContext.getOrCreateKoinScope(
        createLaunchesModules(dependencies)
    )

    private val feature: LaunchesFeature = instanceKeeper.getOrCreate {
        scope.get(parameters = { parametersOf(rocketId) })
    }

    override val state: AnyStateFlow<LaunchesUiState> = feature.state

    override fun onBackClicked() {
        navigateBack()
    }

    override fun onTryAgainClick() {
        feature.onTryAgainClick()
    }
}