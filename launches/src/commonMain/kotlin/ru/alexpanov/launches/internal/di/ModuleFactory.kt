package ru.alexpanov.launches.internal.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.alexpanov.launches.api.LaunchesDependencies

internal fun createLaunchesModules(dependencies: LaunchesDependencies): List<Module> {
    return listOf(
        rocketsModule,
        module {
            factory { dependencies.spaceXApi }
            factory { dependencies.memoryCache }
        }
    )
}