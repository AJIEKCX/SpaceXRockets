package ru.alexpanov.root.internal.di

import ru.alexpanov.core_network.api.DefaultSpaceXApi
import ru.alexpanov.core_network.provider.HttpClientProvider
import ru.alexpanov.core_network.provider.JsonProvider
import ru.alexpanov.launches.api.data.LaunchesMemoryCache
import ru.alexpanov.rockets.api.data.RocketsMemoryCache

internal class DataModule : RootModuleDependencies {
    private val json by lazy { JsonProvider().get() }

    private val httpClient by lazy { HttpClientProvider(json).get() }

    override val spaceXApi by lazy { DefaultSpaceXApi(httpClient) }

    override val rocketsMemoryCache by lazy { RocketsMemoryCache() }

    override val launchesMemoryCache by lazy { LaunchesMemoryCache() }
}