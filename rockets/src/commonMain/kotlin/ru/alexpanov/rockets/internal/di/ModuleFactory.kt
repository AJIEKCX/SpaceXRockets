package ru.alexpanov.rockets.internal.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.alexpanov.rockets.api.RocketsDependencies

internal fun createRocketsModules(dependencies: RocketsDependencies): List<Module> {
    return listOf(
        rocketsModule,
        module {
            factory { dependencies.spaceXApi }
            factory { dependencies.settingsRepository }
            factory { dependencies.memoryCache }
        }
    )
}