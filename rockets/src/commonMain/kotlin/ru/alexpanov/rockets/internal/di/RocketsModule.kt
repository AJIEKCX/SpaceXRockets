package ru.alexpanov.rockets.internal.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.alexpanov.rockets.internal.data.RocketRepository
import ru.alexpanov.rockets.internal.presentation.RocketsFeature

internal val rocketsModule = module {
    factoryOf(::RocketsFeature)
    factoryOf(::RocketRepository)
}