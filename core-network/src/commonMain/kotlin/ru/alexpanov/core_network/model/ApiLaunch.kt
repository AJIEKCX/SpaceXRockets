package ru.alexpanov.core_network.model

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
    @SerialName("date_utc")
    val dateUtc: String,
    @SerialName("success")
    val success: Boolean?,
)