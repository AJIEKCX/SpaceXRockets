package ru.alexpanov.launches.api

import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.launches.api.data.LaunchesUiState

interface Launches {
    val rocketName: String
    val state: AnyStateFlow<LaunchesUiState>

    fun onBackClicked()
}