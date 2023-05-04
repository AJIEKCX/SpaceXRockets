package ru.alexpanov.root.internal.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.alexpanov.core_network.api.DefaultSpaceXApi
import ru.alexpanov.core_network.api.SpaceXApi
import ru.alexpanov.core_network.provider.HttpClientProvider
import ru.alexpanov.core_network.provider.JsonProvider
import ru.alexpanov.launches.api.data.LaunchesMemoryCache
import ru.alexpanov.rockets.api.data.RocketsMemoryCache

internal val dataModule = module {
    factoryOf(::HttpClientProvider)
    singleOf(HttpClientProvider::get)

    factoryOf(::JsonProvider)
    singleOf(JsonProvider::get)

    singleOf(::DefaultSpaceXApi) bind SpaceXApi::class

    singleOf(::RocketsMemoryCache)
    singleOf(::LaunchesMemoryCache)
}