package ru.alexpanov.launches.api.data

sealed class LaunchesUiState {
    object Loading : LaunchesUiState()

    object Error : LaunchesUiState()

    object Empty : LaunchesUiState()

    data class Data(
        val launches: List<LaunchUiModel>
    ) : LaunchesUiState()
}