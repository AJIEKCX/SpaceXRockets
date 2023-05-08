package ru.alexpanov.core_network.provider

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientProvider(
    private val json: Json
) {
    fun get() = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(json)
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("HttpClient: $message")
                }
            }
            level = LogLevel.ALL
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
        private const val endpoint = "https://api.spacexdata.com/"
    }
}