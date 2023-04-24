package ru.alexpanov.rockets.internal.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.alexpanov.core.extension.runCatchingCancellable
import ru.alexpanov.core.feature.CoroutineFeature
import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.core.flow.wrapToAny
import ru.alexpanov.core.model.RocketSettings
import ru.alexpanov.core.repository.SettingsRepository
import ru.alexpanov.rockets.api.data.RocketsUiState
import ru.alexpanov.rockets.internal.data.RocketRepository

internal class RocketsFeature(
    private val rocketRepository: RocketRepository,
    private val settingsRepository: SettingsRepository
) : CoroutineFeature() {
    private val _state = MutableStateFlow<RocketsUiState>(RocketsUiState.Loading)
    val state: AnyStateFlow<RocketsUiState> = _state.wrapToAny()

    init {
        observeSettings()
    }

    private fun observeSettings() {
        settingsRepository
            .observeRocketSettings()
            .onEach { loadRockets(it) }
            .launchIn(coroutineScope)
    }

    private fun loadRockets(settings: RocketSettings) {
        coroutineScope.launch {
            runCatchingCancellable {
                rocketRepository.getRockets()
            }.onSuccess { rockets ->
                _state.value = RocketsUiState.Data(rockets.map { it.toUiModel(settings) })
            }.onFailure {
                // TODO: DELETE
                it.printStackTrace()
                _state.value = RocketsUiState.Error
            }
        }
    }
}