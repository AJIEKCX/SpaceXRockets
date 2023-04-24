package ru.alexpanov.rockets.api

import io.ktor.client.HttpClient
import ru.alexpanov.core.repository.SettingsRepository

interface RocketsDependencies {
    val httpClient: HttpClient
    val settingsRepository: SettingsRepository
}