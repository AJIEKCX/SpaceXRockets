package ru.alexpanov.core_network.model

import kotlinx.datetime.Instant
import kotlinx.datetime.serializers.InstantIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ApiLaunch(
    @SerialName("id")
    val id: String,
    @SerialName("rocket")
    val rocketId: String,
    @SerialName("name")
    val name: String,
    @Serializable(with = InstantIso8601Serializer::class)
    @SerialName("date_utc")
    val dateUtc: Instant,
    @SerialName("success")
    val success: Boolean?,
)