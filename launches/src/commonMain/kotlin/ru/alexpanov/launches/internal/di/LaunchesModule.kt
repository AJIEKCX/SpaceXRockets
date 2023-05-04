package ru.alexpanov.launches.internal.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.alexpanov.launches.internal.data.LaunchesRepository
import ru.alexpanov.launches.internal.presentation.LaunchesFeature

internal val rocketsModule = module {
    factoryOf(::LaunchesFeature)
    factoryOf(::LaunchesRepository)
}