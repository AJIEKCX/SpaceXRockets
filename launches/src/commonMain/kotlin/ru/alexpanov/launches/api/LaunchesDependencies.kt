package ru.alexpanov.launches.api

import io.ktor.client.HttpClient

interface LaunchesDependencies {
    val httpClient: HttpClient
}