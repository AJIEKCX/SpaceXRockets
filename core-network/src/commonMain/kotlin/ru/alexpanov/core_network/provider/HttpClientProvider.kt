package ru.alexpanov.core_network.provider

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.logging.Logger
import kotlinx.serialization.json.Json

class HttpClientProvider(
    private val json: Json
) {
    fun get() = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(json)
        }
        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            url.takeFrom(
                URLBuilder()
                    .takeFrom(endpoint)
                    .apply { encodedPath += url.encodedPath }
            )
        }
    }

    companion object {
        private const val endpoint = "https://api.spacexdata.com/v4/"
    }
}