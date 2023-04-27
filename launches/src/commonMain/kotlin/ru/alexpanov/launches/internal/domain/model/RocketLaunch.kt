package ru.alexpanov.launches.internal.domain.model

data class RocketLaunch(
    val id: String,
    val rocketName: String,
    val launchDate: String,
    val status: LaunchStatus
)

enum class LaunchStatus {
    Success,
    Error,
    Unknown
}