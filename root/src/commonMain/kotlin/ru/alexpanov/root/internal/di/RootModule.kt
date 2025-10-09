package ru.alexpanov.root.internal.di

import ru.alexpanov.core_network.api.SpaceXApi
import ru.alexpanov.launches.api.LaunchesDependencies
import ru.alexpanov.launches.api.data.LaunchesMemoryCache
import ru.alexpanov.rockets.api.RocketsDependencies
import ru.alexpanov.rockets.api.data.RocketsMemoryCache
import ru.alexpanov.root.internal.data.repository.DefaultSettingsRepository
import ru.alexpanov.settings.api.SettingsDependencies

internal class RootModule(val dependencies: RootModuleDependencies) :
    RootModuleDependencies by dependencies,
    RocketsDependencies,
    LaunchesDependencies,
    SettingsDependencies {

    override val settingsRepository by lazy { DefaultSettingsRepository() }
}

internal interface RootModuleDependencies {
    val spaceXApi: SpaceXApi
    val rocketsMemoryCache: RocketsMemoryCache
    val launchesMemoryCache: LaunchesMemoryCache
}