package ru.alexpanov.launches.api

import ru.alexpanov.core_network.api.SpaceXApi
import ru.alexpanov.launches.api.data.LaunchesMemoryCache

interface LaunchesDependencies {
    val spaceXApi: SpaceXApi
    val launchesMemoryCache: LaunchesMemoryCache
}