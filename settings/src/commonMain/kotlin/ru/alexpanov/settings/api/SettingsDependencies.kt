package ru.alexpanov.settings.api

import ru.alexpanov.core.repository.SettingsRepository

interface SettingsDependencies {
    val settingsRepository: SettingsRepository
}