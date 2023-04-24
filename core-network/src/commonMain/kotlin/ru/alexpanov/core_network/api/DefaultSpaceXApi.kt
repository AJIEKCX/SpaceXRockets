package ru.alexpanov.core_network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import ru.alexpanov.core_network.model.ApiRocket

class DefaultSpaceXApi(
    private val httpClient: HttpClient
) : SpaceXApi {
    override suspend fun getRockets(): List<ApiRocket> {
        return httpClient.get {
            url { path("rockets") }
        }.body()
    }
}