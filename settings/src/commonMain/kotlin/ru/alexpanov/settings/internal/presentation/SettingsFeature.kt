package ru.alexpanov.settings.internal.presentation

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.alexpanov.core.feature.CoroutineFeature
import ru.alexpanov.core.model.DistanceUnit
import ru.alexpanov.core.model.MassUnit
import ru.alexpanov.core.model.RocketSettings
import ru.alexpanov.core.repository.SettingsRepository

class SettingsFeature(
    private val settingsRepository: SettingsRepository
) : CoroutineFeature() {
    val state: StateFlow<RocketSettings> = settingsRepository
        .observeRocketSettings()
        .stateIn(
            coroutineScope,
            SharingStarted.Eagerly,
            settingsRepository.currentSettings
        )

    fun onHeightChanged(value: DistanceUnit) {
        settingsRepository.updateSettings(state.value.copy(height = value))
    }

    fun onDiameterChanged(value: DistanceUnit) {
        settingsRepository.updateSettings(state.value.copy(diameter = value))
    }

    fun onMassChanged(value: MassUnit) {
        settingsRepository.updateSettings(state.value.copy(mass = value))
    }

    fun onPayloadWeightChanged(value: MassUnit) {
        settingsRepository.updateSettings(state.value.copy(payloadWeight = value))
    }
}