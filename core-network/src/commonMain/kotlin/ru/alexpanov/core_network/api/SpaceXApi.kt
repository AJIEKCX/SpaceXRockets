package ru.alexpanov.core_network.api

import ru.alexpanov.core_network.model.ApiLaunch
import ru.alexpanov.core_network.model.ApiRocket

interface SpaceXApi {
    suspend fun getRockets(): List<ApiRocket>
    suspend fun getLaunches(): List<ApiLaunch>
}