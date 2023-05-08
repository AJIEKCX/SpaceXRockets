package ru.alexpanov.launches.internal.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.alexpanov.core.extension.runCatchingCancellable
import ru.alexpanov.core.feature.CoroutineFeature
import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.core.flow.wrapToAny
import ru.alexpanov.launches.api.data.LaunchesUiState
import ru.alexpanov.launches.internal.data.LaunchesRepository

internal class LaunchesFeature(
    private val rocketId: String,
    private val launchesRepository: LaunchesRepository
) : CoroutineFeature() {
    private val _state = MutableStateFlow<LaunchesUiState>(LaunchesUiState.Loading)
    val state: AnyStateFlow<LaunchesUiState> = _state.wrapToAny()

    init {
        loadLaunches()
    }

    fun onTryAgainClick() {
        loadLaunches()
    }

    private fun loadLaunches() {
        coroutineScope.launch {
            _state.value = LaunchesUiState.Loading
            runCatchingCancellable {
                launchesRepository.getLaunches(rocketId)
            }.onSuccess { launches ->
                if (launches.isEmpty()) {
                    _state.value = LaunchesUiState.Empty
                } else {
                    _state.value = LaunchesUiState.Data(launches.map { it.toUiModel() })
                }
            }.onFailure {
                _state.value = LaunchesUiState.Error
            }
        }
    }
}