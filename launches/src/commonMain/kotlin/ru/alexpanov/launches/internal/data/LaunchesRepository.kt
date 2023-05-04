package ru.alexpanov.launches.internal.data

import ru.alexpanov.core_network.api.SpaceXApi
import ru.alexpanov.launches.api.data.LaunchesMemoryCache
import ru.alexpanov.launches.internal.domain.model.RocketLaunch

internal class LaunchesRepository(
    private val spaceXApi: SpaceXApi,
    private val memoryCache: LaunchesMemoryCache
) {
    suspend fun getLaunches(rocketId: String): List<RocketLaunch> {
        return memoryCache[rocketId] ?: spaceXApi.getLaunches()
            .filter { it.rocketId == rocketId }
            .map { it.toDomain() }
            .also { memoryCache[rocketId] = it }
    }
}