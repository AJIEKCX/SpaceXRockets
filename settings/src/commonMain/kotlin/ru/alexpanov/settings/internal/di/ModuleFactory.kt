package ru.alexpanov.settings.internal.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.alexpanov.settings.api.SettingsDependencies

internal fun createSettingsModules(dependencies: SettingsDependencies): List<Module> {
    return listOf(
        settingsModule,
        module {
            factory { dependencies.settingsRepository }
        }
    )
}