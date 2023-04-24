package ru.alexpanov.rockets.api

import ru.alexpanov.core.flow.AnyStateFlow
import ru.alexpanov.rockets.api.data.RocketsUiState

interface Rockets {
    val state: AnyStateFlow<RocketsUiState>

    fun onShowLaunchesClicked(rocketId: String)
}