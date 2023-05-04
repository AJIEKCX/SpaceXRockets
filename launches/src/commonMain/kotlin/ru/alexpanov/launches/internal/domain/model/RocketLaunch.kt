package ru.alexpanov.launches.internal.domain.model

import kotlinx.datetime.LocalDate

internal data class RocketLaunch(
    val id: String,
    val rocketName: String,
    val launchDate: LocalDate,
    val status: LaunchStatus
)

enum class LaunchStatus {
    Success,
    Error,
    Unknown
}