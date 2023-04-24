package ru.alexpanov.root.internal.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.alexpanov.core.model.DistanceUnit
import ru.alexpanov.core.model.MassUnit
import ru.alexpanov.core.model.RocketSettings
import ru.alexpanov.core.repository.SettingsRepository

internal class DefaultSettingsRepository : SettingsRepository {
    private val rocketSettings = MutableStateFlow(
        RocketSettings(
            mass = MassUnit.Kg,
            height = DistanceUnit.Meters,
            diameter = DistanceUnit.Meters,
            payloadWeight = MassUnit.Kg
        )
    )

    override val currentSettings: RocketSettings = rocketSettings.value

    override fun updateSettings(settings: RocketSettings) {
        rocketSettings.value = settings
    }

    override fun observeRocketSettings(): StateFlow<RocketSettings> {
        return rocketSettings.asStateFlow()
    }
}