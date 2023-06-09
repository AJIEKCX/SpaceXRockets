package ru.alexpanov.launches.internal.data

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import ru.alexpanov.core_network.model.ApiLaunch
import ru.alexpanov.launches.internal.domain.model.LaunchStatus
import ru.alexpanov.launches.internal.domain.model.RocketLaunch

internal fun ApiLaunch.toDomain(): RocketLaunch {
    return RocketLaunch(
        id = id,
        rocketName = name,
        launchDate = dateUtc.toLocalDateTime(TimeZone.UTC).date,
        status = when (success) {
            true -> LaunchStatus.Success
            false -> LaunchStatus.Error
            null -> LaunchStatus.Unknown
        }
    )
}