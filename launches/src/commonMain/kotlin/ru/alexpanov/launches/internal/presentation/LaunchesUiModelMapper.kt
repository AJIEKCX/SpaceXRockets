package ru.alexpanov.launches.internal.presentation

import ru.alexpanov.core.date.DatePattern
import ru.alexpanov.core.extension.format
import ru.alexpanov.launches.api.data.LaunchUiModel
import ru.alexpanov.launches.internal.domain.model.RocketLaunch

internal fun RocketLaunch.toUiModel(): LaunchUiModel {
    return LaunchUiModel(
        id = id,
        name = rocketName,
        launchDate = launchDate.format(DatePattern.D_MMM_YYYY),
        status = status
    )
}