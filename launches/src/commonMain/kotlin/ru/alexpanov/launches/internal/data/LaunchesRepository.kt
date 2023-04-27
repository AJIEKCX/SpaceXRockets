package ru.alexpanov.launches.internal.data

import ru.alexpanov.core_network.api.SpaceXApi
import ru.alexpanov.launches.internal.domain.model.RocketLaunch

internal class LaunchesRepository(
    private val spaceXApi: SpaceXApi
) {
    private var launches: List<RocketLaunch>? = null

    suspend fun getLaunches(rocketId: String): List<RocketLaunch> {
        return launches ?: spaceXApi.getLaunches()
            .filter { it.rocketId == rocketId }
            .map { it.toDomain() }
            .also { launches = it }
    }
}