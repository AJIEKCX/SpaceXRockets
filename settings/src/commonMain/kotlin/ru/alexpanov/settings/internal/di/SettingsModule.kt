package ru.alexpanov.settings.internal.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.alexpanov.settings.internal.presentation.SettingsFeature

internal val settingsModule = module {
    factoryOf(::SettingsFeature)
}