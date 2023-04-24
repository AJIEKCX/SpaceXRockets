package ru.alexpanov.core.repository

import kotlinx.coroutines.flow.StateFlow
import ru.alexpanov.core.model.RocketSettings

interface SettingsRepository {
    val currentSettings: RocketSettings

    fun updateSettings(settings: RocketSettings)
    fun observeRocketSettings(): StateFlow<RocketSettings>
}