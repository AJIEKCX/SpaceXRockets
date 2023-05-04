package ru.alexpanov.rockets.internal.data

import ru.alexpanov.core_network.api.SpaceXApi
import ru.alexpanov.rockets.api.data.RocketsMemoryCache
import ru.alexpanov.rockets.internal.domain.model.Rocket

internal class RocketRepository(
    private val spaceXApi: SpaceXApi,
    private val memoryCache: RocketsMemoryCache
) {
    suspend fun getRockets(): List<Rocket> {
        return memoryCache.get() ?: spaceXApi
            .getRockets()
            .map { it.toDomain() }
            .also { memoryCache.set(value = it) }
    }
}