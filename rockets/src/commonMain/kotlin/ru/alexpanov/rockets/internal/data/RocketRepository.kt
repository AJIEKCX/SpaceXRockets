package ru.alexpanov.rockets.internal.data

import ru.alexpanov.core_network.api.SpaceXApi
import ru.alexpanov.rockets.internal.domain.model.Rocket

internal class RocketRepository(
    private val spaceXApi: SpaceXApi
) {
    private var rockets: List<Rocket>? = null

    suspend fun getRockets(): List<Rocket> {
        return rockets ?: spaceXApi
            .getRockets()
            .map { it.toDomain() }
            .also { rockets = it }
    }
}