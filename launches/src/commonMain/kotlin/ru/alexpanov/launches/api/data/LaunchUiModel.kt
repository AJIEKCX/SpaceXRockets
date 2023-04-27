package ru.alexpanov.launches.api.data

import ru.alexpanov.launches.internal.domain.model.LaunchStatus

data class LaunchUiModel(
    val id: String,
    val name: String,
    val launchDate: String,
    val status: LaunchStatus
)