package ru.alexpanov.launches.internal.presentation

import ru.alexpanov.launches.api.data.LaunchUiModel
import ru.alexpanov.launches.internal.domain.model.RocketLaunch

internal fun RocketLaunch.toUiModel(): LaunchUiModel {
    return LaunchUiModel(
        id = id,
        name = rocketName,
        launchDate = launchDate,
        status = status
    )
}