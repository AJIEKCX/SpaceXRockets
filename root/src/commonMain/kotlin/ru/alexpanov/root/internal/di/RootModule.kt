package ru.alexpanov.root.internal.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.alexpanov.core.repository.SettingsRepository
import ru.alexpanov.core_network.api.SpaceXApi
import ru.alexpanov.launches.api.LaunchesDependencies
import ru.alexpanov.launches.api.data.LaunchesMemoryCache
import ru.alexpanov.rockets.api.RocketsDependencies
import ru.alexpanov.rockets.api.data.RocketsMemoryCache
import ru.alexpanov.root.internal.data.repository.DefaultSettingsRepository
import ru.alexpanov.settings.api.SettingsDependencies

internal val rootModule = module {
    singleOf(::DefaultSettingsRepository) bind SettingsRepository::class
    singleOf(::DefaultRocketsDependencies) bind RocketsDependencies::class
    singleOf(::DefaultLaunchesDependencies) bind LaunchesDependencies::class
    singleOf(::DefaultSettingsDependencies) bind SettingsDependencies::class
}

private class DefaultRocketsDependencies(
    override val spaceXApi: SpaceXApi,
    override val settingsRepository: SettingsRepository,
    override val memoryCache: RocketsMemoryCache
) : RocketsDependencies

private class DefaultLaunchesDependencies(
    override val spaceXApi: SpaceXApi,
    override val memoryCache: LaunchesMemoryCache
) : LaunchesDependencies

private class DefaultSettingsDependencies(
    override val settingsRepository: SettingsRepository
) : SettingsDependencies