package ru.alexpanov.rockets.api

import ru.alexpanov.core.repository.SettingsRepository
import ru.alexpanov.core_network.api.SpaceXApi
import ru.alexpanov.rockets.api.data.RocketsMemoryCache

interface RocketsDependencies {
    val spaceXApi: SpaceXApi
    val settingsRepository: SettingsRepository
    val rocketsMemoryCache: RocketsMemoryCache
}