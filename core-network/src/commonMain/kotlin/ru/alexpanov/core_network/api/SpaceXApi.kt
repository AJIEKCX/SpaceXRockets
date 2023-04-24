package ru.alexpanov.core_network.api

import ru.alexpanov.core_network.model.ApiRocket

interface SpaceXApi {
    suspend fun getRockets(): List<ApiRocket>
}