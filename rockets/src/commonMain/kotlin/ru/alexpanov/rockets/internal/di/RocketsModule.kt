package ru.alexpanov.rockets.internal.di

import ru.alexpanov.rockets.api.RocketsDependencies
import ru.alexpanov.rockets.internal.data.RocketRepository
import ru.alexpanov.rockets.internal.presentation.RocketsFeature

internal class RocketsModule(rocketDependencies: RocketsDependencies) :
    RocketsDependencies by rocketDependencies {
    private val rocketRepository get() = RocketRepository(spaceXApi, rocketsMemoryCache)

    val rocketsFeature get() = RocketsFeature(rocketRepository, settingsRepository)
}