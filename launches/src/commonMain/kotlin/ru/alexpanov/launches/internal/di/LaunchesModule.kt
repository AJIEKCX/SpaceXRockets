package ru.alexpanov.launches.internal.di

import ru.alexpanov.launches.api.LaunchesDependencies
import ru.alexpanov.launches.internal.data.LaunchesRepository
import ru.alexpanov.launches.internal.presentation.LaunchesFeature

internal class LaunchesModule(launchDependencies: LaunchesDependencies) :
    LaunchesDependencies by launchDependencies {
    private val launchesRepository get() = LaunchesRepository(spaceXApi, launchesMemoryCache)

    fun launchesFeature(rocketId: String) = LaunchesFeature(rocketId, launchesRepository)
}